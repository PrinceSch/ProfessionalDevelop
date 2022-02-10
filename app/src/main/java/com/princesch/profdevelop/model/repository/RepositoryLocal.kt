package com.princesch.profdevelop.model.repository

import com.princesch.profdevelop.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}
