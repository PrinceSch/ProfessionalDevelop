package com.princesch.profdevelop.view.main

import com.princesch.profdevelop.model.data.AppState
import com.princesch.profdevelop.model.data.DataModel
import com.princesch.profdevelop.model.repository.Repository
import com.princesch.profdevelop.presenter.Interactor
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
