package com.teka.chamaa_finance.di

import com.teka.chamaa_finance.data_layer.datasource.NoteLocalDataSource
import com.teka.chamaa_finance.data_layer.datasource.NoteLocalDataSourceImpl
import com.teka.chamaa_finance.data_layer.repository.NoteRepository
import com.teka.chamaa_finance.data_layer.repository.NoteRepositoryImpl
import com.teka.chamaa_finance.domain.usecase.CreateNoteUseCase
import com.teka.chamaa_finance.domain.usecase.DeleteNoteUseCase
import com.teka.chamaa_finance.domain.usecase.GetAllNotesUseCase
import com.teka.chamaa_finance.domain.usecase.GetNoteUseCase
import com.teka.chamaa_finance.domain.usecase.UpdateNoteUseCase
import com.teka.chamaa_finance.networking.ApiService
import com.teka.chamaa_finance.networking.InsultCensorClient
import com.teka.chamaa_finance.networking.createHttpClient
import com.teka.chamaa_finance.screens.censor.CensorViewModel
import com.teka.chamaa_finance.screens.viewmodel.CreateNoteViewModel
import com.teka.chamaa_finance.screens.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module


val provideDataSourceModule = module {
    singleOf(::NoteLocalDataSourceImpl).bind(NoteLocalDataSource::class)
}

val provideRepositoryModule = module {
    singleOf(::NoteRepositoryImpl).bind(NoteRepository::class)
}

val provideViewModelModule = module {
    viewModelOf(::CreateNoteViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::CensorViewModel)
}

val provideUseCaseModule = module {
    singleOf(::CreateNoteUseCase)
    singleOf(::GetAllNotesUseCase)
    singleOf(::DeleteNoteUseCase)
    singleOf(::UpdateNoteUseCase)
    singleOf(::GetNoteUseCase)
}

val provideKtorClient = module {
    singleOf(::InsultCensorClient)
}

val provideApiService = module {
    singleOf(::ApiService)
}


