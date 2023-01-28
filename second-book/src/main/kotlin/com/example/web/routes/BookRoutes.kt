package com.example.web.routes

import com.example.core.Book
import com.example.core.facades.BookFacade
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.bookRouting() {
    val bookFacade = BookFacade()
    route("api/books") {
        get {
            call.respond(bookFacade.fetchBooks())
        }
        get("{id?}") {
            val id = call.parameters["id"]
            val responseBook = bookFacade.fetchBook(id!!)
            if (responseBook != null) {
                call.respond(responseBook)
            } else {
                call.respond("No book with $id")
            }
        }
        post {
            val requestBook = call.receive<Book>()
            bookFacade.saveBook(requestBook)
            call.respond("Saved")
        }
        put {
            val updatedBook = call.receive<Book>()
            bookFacade.updateBook(updatedBook)
            call.respond("Updated")
        }
        delete("{id}") {
            val id = call.parameters["id"]
            bookFacade.deleteBook(id!!)
            call.respond("Deleted")
        }
    }
}
