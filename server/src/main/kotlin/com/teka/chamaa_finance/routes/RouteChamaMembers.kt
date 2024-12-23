package com.teka.chamaa_finance.routes


import com.teka.chamaa_finance.domain.repositories.impl.ChamaMembersRepositoryImpl
import com.teka.chamaa_finance.dtos.ChamaMembersDTO
import com.teka.chamaa_finance.util.GenericResponse
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.serialization.JsonConvertException
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.chamaaMembersRoutes() {
    val repository = ChamaMembersRepositoryImpl()


    route("/chamaaMembers") {
        get {
            val chamaaMembers = repository.allChamaaMembers()
            call.respond(chamaaMembers)
        }

        get("/byId/{chamaMemberId}") {
            val id = call.parameters["chamaMemberId"]
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
            val task = repository.chamaaMemberById(id)
            if (task == null) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }
            call.respond(task)
        }

        post {
            try {
                val chamaaMembers = call.receive<ChamaMembersDTO>()
                repository.addChamaaMember(chamaaMembers)
                call.respond(HttpStatusCode.NoContent)
            } catch (ex: IllegalStateException) {
                call.respond(HttpStatusCode.BadRequest)
            } catch (ex: JsonConvertException) {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        delete("/{chamaaMemberId}") {
            val id = call.parameters["chamaaMemberId"]
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }
            if (repository.removeChamaaMember(id)) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}