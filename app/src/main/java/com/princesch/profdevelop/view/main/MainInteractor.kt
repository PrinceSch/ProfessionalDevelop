package com.princesch.profdevelop.view.main

import com.princesch.profdevelop.model.data.AppState
import com.princesch.profdevelop.model.data.DataModel
import com.princesch.profdevelop.model.repository.Repository
import com.princesch.profdevelop.model.repository.RepositoryLocal
import com.princesch.profdevelop.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}
