package com.princesch.profdevelop.model.datasource

import com.princesch.profdevelop.model.data.AppState
import com.princesch.profdevelop.model.data.DataModel
import io.reactivex.Observable

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}
