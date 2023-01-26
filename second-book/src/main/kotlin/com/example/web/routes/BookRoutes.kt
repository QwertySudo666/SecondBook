package com.example.web.routes

import com.example.core.Book
import com.example.persistance.Database
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.bookRouting() {
    val database = Database()
    route("api/books") {
        get("") {
            call.respond(database.bookStorage)
        }
        get("{id}") {
            val id = call.parameters["id"]
            val responseBook = database.bookStorage.find { it.id == id }
            if (responseBook != null) {
                call.respond(responseBook)
            } else {
                call.respond("No book with $id")
            }
        }
        post("") {
            val requestBook = call.receive<Book>()
            database.bookStorage.add(requestBook)
            call.respond("Saved")
        }
        put("{id}") {
            val id = call.parameters["id"]
            val updatedBook = call.receive<Book>()
            database.bookStorage.removeIf { it.id == id }
            database.bookStorage.add(updatedBook)
            call.respond("Updated")
        }
        delete("{id}") {
            val id = call.parameters["id"]
            database.bookStorage.removeIf { it.id == id }
            call.respond("Deleted")
        }
    }
}
