package com.princesch.repository

import com.princesch.model.DataModel
import com.princesch.repository.datasource.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<com.princesch.model.DataModel>> {

    override suspend fun getData(word: String): List<com.princesch.model.DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: com.princesch.model.AppState) {
        dataSource.saveToDB(appState)
    }
}