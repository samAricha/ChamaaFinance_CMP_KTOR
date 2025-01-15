package com.teka.chamaa_finance.routes


import com.teka.chamaa_finance.domain.repositories.impl.ChamaRepositoryImpl
import com.teka.chamaa_finance.dtos.ApiResponseHandler
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
import java.sql.BatchUpdateException

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
                val savedChamaa = repository.addChamaa(chamaa)

                val response = ApiResponseHandler(
                    isSuccessful = true,
                    status = "OK",
                    message = "Chama created successfully.",
                    data = savedChamaa
                )

                call.respond(HttpStatusCode.Created, response)

            } catch (ex: Exception) {
                println("chama exception:: $ex")

                val errorMessage = when (ex) {
                    is BatchUpdateException -> "Database error: ${ex.localizedMessage?.split(":")?.last()?.trim() ?: "Unknown database error"}"
                    else -> ex.localizedMessage ?: "An unexpected error occurred"
                }

                val response = ApiResponseHandler(
                    isSuccessful = false,
                    status = "ERROR",
                    message = errorMessage,
                    data = null
                )

                call.respond(HttpStatusCode.InternalServerError, response)
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