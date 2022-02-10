package com.princesch.repository

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: com.princesch.model.AppState)
}
