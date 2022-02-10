package com.princesch.profdevelop.viewmodel

import androidx.lifecycle.LiveData
import com.princesch.core.BaseViewModel
import com.princesch.model.AppState
import com.princesch.profdevelop.utils.parseOnlineSearchResults
import com.princesch.profdevelop.view.main.MainInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val interactor: MainInteractor) :
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

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _mutableLiveData.postValue(parseOnlineSearchResults(interactor.getData(word, isOnline)))
        }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(com.princesch.model.AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = com.princesch.model.AppState.Success(null)
        super.onCleared()
    }
}
