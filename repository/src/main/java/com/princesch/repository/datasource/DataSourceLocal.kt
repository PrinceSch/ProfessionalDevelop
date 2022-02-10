package com.princesch.repository.datasource

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: com.princesch.model.AppState)
}
