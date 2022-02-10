package com.princesch.profdevelop.view.main

import com.princesch.core.Interactor
import com.princesch.model.AppState

class MainInteractor(
    private val repositoryRemote: com.princesch.repository.Repository<List<com.princesch.model.DataModel>>,
    private val repositoryLocal: com.princesch.repository.RepositoryLocal<List<com.princesch.model.DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(
        word: String,
        fromRemoteSource: Boolean
    ): com.princesch.model.AppState {
        val appState: com.princesch.model.AppState
        if (fromRemoteSource) {
            appState = com.princesch.model.AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = com.princesch.model.AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}
