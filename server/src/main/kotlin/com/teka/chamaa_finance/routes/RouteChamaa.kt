package com.teka.chamaa_finance.routes


import com.teka.chamaa_finance.domain.repositories.impl.ChamaRepositoryImpl
import com.teka.chamaa_finance.dtos.ChamaDTO
import com.teka.chamaa_finance.util.GenericResponse
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.serialization.JsonConvertException
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.chamaaRoutes() {
    val repository = ChamaRepositoryImpl()


    route("/chamaas") {
        get {
            val tasks = repository.allChamaas()
            call.respond(tasks)
        }

        get("/byId/{chamaaId}") {
            val id = call.parameters["chamaaId"]
            if (id == null) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    GenericResponse(
                        isSuccess = true,
                        message = "bad request",
                        data = null
                    )
                )
                return@get
            }
            val task = repository.chamaaById(id)
            if (task == null) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }
            call.respond(task)
        }

        post {
            try {
                val chamaa = call.receive<ChamaDTO>()
                repository.addChamaa(chamaa)
                call.respond(HttpStatusCode.NoContent)
            } catch (ex: IllegalStateException) {
                call.respond(HttpStatusCode.BadRequest)
            } catch (ex: JsonConvertException) {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        delete("/{chamaaId}") {
            val id = call.parameters["chamaaId"]
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }
            if (repository.removeChamaa(id)) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}