package com.teka.chamaa_finance.di

import com.teka.chamaa_finance.data_layer.database.PeopleDatabase
import com.teka.chamaa_finance.data_layer.database.getPeopleDatabase
import org.koin.dsl.module

actual val platformModule = module {
    single<PeopleDatabase> { getPeopleDatabase() }
}