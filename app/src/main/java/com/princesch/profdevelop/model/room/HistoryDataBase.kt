package com.princesch.profdevelop.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(HistoryEntity::class), version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {
    // Возвращаем DAO
    abstract fun historyDao(): HistoryDao
}