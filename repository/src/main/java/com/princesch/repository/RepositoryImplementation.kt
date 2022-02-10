package com.princesch.repository

import com.princesch.model.DataModel
import com.princesch.repository.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<com.princesch.model.DataModel>> {

    override suspend fun getData(word: String): List<com.princesch.model.DataModel> {
        return dataSource.getData(word)
    }
}