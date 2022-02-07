package com.princesch.profdevelop.model.repository

import com.princesch.profdevelop.model.data.DataModel
import com.princesch.profdevelop.model.datasource.DataSource
import io.reactivex.Observable

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}