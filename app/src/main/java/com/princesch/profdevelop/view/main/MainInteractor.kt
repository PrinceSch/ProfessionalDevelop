package com.princesch.profdevelop.view.main

import com.princesch.profdevelop.model.data.AppState
import com.princesch.profdevelop.model.data.DataModel
import com.princesch.profdevelop.model.repository.Repository
import com.princesch.profdevelop.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
