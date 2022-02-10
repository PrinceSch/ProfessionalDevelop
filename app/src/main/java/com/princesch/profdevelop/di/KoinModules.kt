package com.princesch.profdevelop.di

import androidx.room.Room
import com.princesch.profdevelop.model.data.DataModel
import com.princesch.profdevelop.model.datasource.RetrofitImplementation
import com.princesch.profdevelop.model.datasource.RoomDataBaseImplementation
import com.princesch.profdevelop.model.repository.Repository
import com.princesch.profdevelop.model.repository.RepositoryImplementation
import com.princesch.profdevelop.model.repository.RepositoryImplementationLocal
import com.princesch.profdevelop.model.repository.RepositoryLocal
import com.princesch.profdevelop.model.room.HistoryDataBase
import com.princesch.profdevelop.view.history.HistoryInteractor
import com.princesch.profdevelop.view.history.HistoryViewModel
import com.princesch.profdevelop.view.main.MainInteractor
import com.princesch.profdevelop.viewmodel.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
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