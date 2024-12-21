package com.teka.chamaa_finance

import com.teka.chamaa_finance.model.TaskRepositoryImpl
import com.teka.chamaa_finance.plugins.configureDataBase
import com.teka.chamaa_finance.plugins.configureRouting
import com.teka.organiks.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*


fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val repository = TaskRepositoryImpl()

    configureSerialization()
    configureDataBase()
    configureRouting(repository)
}

