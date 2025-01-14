package com.teka.chamaa_finance.routes


import com.teka.chamaa_finance.domain.repositories.impl.ContributionsRepositoryImpl
import com.teka.chamaa_finance.dtos.ApiResponseHandler
import com.teka.chamaa_finance.dtos.ContributionDTO
import com.teka.chamaa_finance.util.GenericResponse
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.serialization.JsonConvertException
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.contributionRoutes() {
    val repository = ContributionsRepositoryImpl()


    route("/contributions") {
        get {
            val contributions = repository.allContributions()
            call.respond(contributions)
        }

        get("/byId/{contributionId}") {
            val id = call.parameters["contributionId"]
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
            val task = repository.contributionById(id)
            if (task == null) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }
            call.respond(task)
        }


        post {
            try {
                val task = call.receive<ContributionDTO>()
                val savedContribution = repository.addContribution(task)
                call.respond(HttpStatusCode.Created, ApiResponseHandler(true, "SUCCESS", "Contribution added successfully", savedContribution))
            } catch (ex: Exception) {
                call.respond(HttpStatusCode.BadRequest, ApiResponseHandler(false, "ERROR", ex.localizedMessage, null))
            }
        }


        delete("/{contributionId}") {
            val id = call.parameters["contributionId"]
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }
            if (repository.removeContribution(id)) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}