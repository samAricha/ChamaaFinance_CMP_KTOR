package com.teka.chamaa_finance.di


import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            platformModule,
            provideDataSourceModule,
            provideRepositoryModule,
            provideViewModelModule,
            provideUseCaseModule,
            provideKtorClient
        )
    }
}