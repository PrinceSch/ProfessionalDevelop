package com.princesch.profdevelop.di

import androidx.room.Room
import com.princesch.repository.datasource.RetrofitImplementation
import com.princesch.repository.datasource.RoomDataBaseImplementation
import com.princesch.repository.room.HistoryDataBase
import com.princesch.historyscreen.HistoryInteractor
import com.princesch.historyscreen.HistoryViewModel
import com.princesch.profdevelop.view.main.MainInteractor
import com.princesch.profdevelop.viewmodel.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<com.princesch.repository.Repository<List<com.princesch.model.DataModel>>> {
        com.princesch.repository.RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<com.princesch.repository.RepositoryLocal<List<com.princesch.model.DataModel>>> {
        com.princesch.repository.RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}