package com.princesch.profdevelop.di

import com.princesch.profdevelop.model.data.DataModel
import com.princesch.profdevelop.model.datasource.RetrofitImplementation
import com.princesch.profdevelop.model.datasource.RoomDataBaseImplementation
import com.princesch.profdevelop.model.repository.Repository
import com.princesch.profdevelop.model.repository.RepositoryImplementation
import com.princesch.profdevelop.view.main.MainInteractor
import com.princesch.profdevelop.viewmodel.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImplementation(
        RetrofitImplementation()
    ) }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImplementation(
        RoomDataBaseImplementation()
    ) }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}