package com.princesch.repository.datasource

import com.princesch.profdevelop.utils.convertDataModelSuccessToEntity
import com.princesch.profdevelop.utils.mapHistoryEntityToSearchResult
import com.princesch.repository.room.HistoryDao

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<com.princesch.model.DataModel>> {

    override suspend fun getData(word: String): List<com.princesch.model.DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: com.princesch.model.AppState) {
        historyDao.insert(convertDataModelSuccessToEntity(appState))
    }
}