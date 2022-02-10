package com.princesch.historyscreen

import androidx.lifecycle.LiveData
import com.princesch.core.BaseViewModel
import com.princesch.model.AppState
import com.princesch.profdevelop.utils.parseLocalSearchResults
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<com.princesch.model.AppState> = _mutableLiveData

    fun subscribe(): LiveData<com.princesch.model.AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = com.princesch.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(com.princesch.model.AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value =
            com.princesch.model.AppState.Success(null)//Set View to original state in onStop
        super.onCleared()
    }
}