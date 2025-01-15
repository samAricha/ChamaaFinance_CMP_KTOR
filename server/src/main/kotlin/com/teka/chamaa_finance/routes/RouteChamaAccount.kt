package com.teka.chamaa_finance.routes


import com.teka.chamaa_finance.domain.repositories.impl.ChamaAccountRepositoryImpl
import com.teka.chamaa_finance.dtos.ApiResponseHandler
import com.teka.chamaa_finance.dtos.ChamaAccountDTO
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

fun Route.chamaAccountRoutes() {
    val repository = ChamaAccountRepositoryImpl()


    route("/chamaAccounts") {
        get {
            val chamaAccounts = repository.allChamaaAccount()
            call.respond(chamaAccounts)
        }

        get("/byId/{chamaAccountId}") {
            val id = call.parameters["chamaAccountId"]
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
            val task = repository.chamaaAccountById(id)
            if (task == null) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }
            call.respond(task)
        }

        post {
            try {
                val chamaaAccount = call.receive<ChamaAccountDTO>()
                val savedChamaaAccount = repository.addChamaaAccount(chamaaAccount)

                val response = ApiResponseHandler(
                    isSuccessful = true,
                    status = "OK",
                    message = "Chama account created successfully.",
                    data = savedChamaaAccount
                )

                call.respond(HttpStatusCode.Created, response)

            } catch (ex: Exception) {
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

        delete("/{chamaAccountId}") {
            val id = call.parameters["chamaAccountId"]
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }
            if (repository.removeChamaaAccount(id)) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}