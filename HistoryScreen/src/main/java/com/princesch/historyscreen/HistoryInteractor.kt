package com.princesch.historyscreen

import com.princesch.core.Interactor
import com.princesch.model.AppState

class HistoryInteractor(
    private val repositoryRemote: com.princesch.repository.Repository<List<com.princesch.model.DataModel>>,
    private val repositoryLocal: com.princesch.repository.RepositoryLocal<List<com.princesch.model.DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): com.princesch.model.AppState {
        return com.princesch.model.AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}