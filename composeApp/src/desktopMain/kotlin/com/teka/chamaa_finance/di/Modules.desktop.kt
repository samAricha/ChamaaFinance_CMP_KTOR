package com.teka.chamaa_finance.di

import com.teka.chamaa_finance.data_layer.database.PeopleDatabase
import com.teka.chamaa_finance.data_layer.database.getPeopleDatabase
import com.teka.chamaa_finance.networking.createHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual val platformModule = module {
    single<PeopleDatabase> { getPeopleDatabase() }
    single<HttpClient> { createHttpClient(OkHttp.create()) }
}