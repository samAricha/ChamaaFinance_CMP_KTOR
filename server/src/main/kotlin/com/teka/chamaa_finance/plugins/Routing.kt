package com.teka.chamaa_finance.plugins

import com.teka.chamaa_finance.domain.repositories.TaskRepository
import com.teka.chamaa_finance.routes.chamaAccountRoutes
import com.teka.chamaa_finance.routes.chamaaMembersRoutes
import com.teka.chamaa_finance.routes.chamaaRoutes
import com.teka.chamaa_finance.routes.contributionRoutes
import com.teka.chamaa_finance.routes.memberRoutes
import com.teka.chamaa_finance.routes.taskRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(repository: TaskRepository) {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        taskRoutes()
        chamaaRoutes()
        chamaAccountRoutes()
        chamaaMembersRoutes()
        contributionRoutes()
        memberRoutes()
    }
}
