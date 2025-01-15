package com.teka.chamaa_finance.routes


import com.teka.chamaa_finance.model.Priority
import com.teka.chamaa_finance.model.Task
import com.teka.chamaa_finance.domain.repositories.impl.TaskRepositoryImpl
import com.teka.chamaa_finance.util.GenericResponse
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.serialization.JsonConvertException
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.taskRoutes() {
    val repository = TaskRepositoryImpl()


    route("/tasks") {
        get {
            val tasks = repository.allTasks()
            call.respond(tasks)
        }

        get("/byName/{taskName}") {
            val name = call.parameters["taskName"]
            if (name == null) {
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
            val task = repository.taskByName(name)
            if (task == null) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }
            call.respond(task)
        }

        get("/byPriority/{priority}") {
            val priorityAsText = call.parameters["priority"]
            if (priorityAsText == null) {
                call.respond(
                    HttpStatusCode.BadRequest
                )
                return@get
            }
            try {
                val priority = Priority.valueOf(priorityAsText)
                val tasks = repository.tasksByPriority(priority)


                if (tasks.isEmpty()) {
                    call.respond(
                        HttpStatusCode.NotFound,
                        GenericResponse(
                            isSuccess = false,
                            message = "task not found",
                            data = null
                        )
                    )
                    return@get
                }
                call.respond(tasks)
            } catch (ex: IllegalArgumentException) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    GenericResponse(
                        isSuccess = false,
                        message = "bad request",
                        data = "${ex.localizedMessage}"
                    ))
            }
        }

        post {
            try {
                val task = call.receive<Task>()
                repository.addTask(task)
                call.respond(HttpStatusCode.NoContent)
            } catch (ex: IllegalStateException) {
                call.respond(HttpStatusCode.BadRequest)
            } catch (ex: JsonConvertException) {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        delete("/{taskName}") {
            val name = call.parameters["taskName"]
            if (name == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }
            if (repository.removeTask(name)) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}