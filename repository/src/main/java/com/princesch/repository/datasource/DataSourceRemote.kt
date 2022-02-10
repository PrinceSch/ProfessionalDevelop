package com.princesch.repository.datasource

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<com.princesch.model.DataModel>> {

    override suspend fun getData(word: String): List<com.princesch.model.DataModel> = remoteProvider.getData(word)
}