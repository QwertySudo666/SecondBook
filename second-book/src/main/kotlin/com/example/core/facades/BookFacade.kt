package com.example.core.facades

import com.example.core.Book
import com.example.persistance.Database

class BookFacade {
    private val database = Database()

    fun fetchBooks(): List<Book> {
        return database.bookStorage
    }

    fun fetchBook(id: String): Book? {
        return database.bookStorage.find { it.id == id }
    }

    fun saveBook(requestBook: Book) {
        database.bookStorage.add(requestBook)
    }

    fun updateBook(updatedBook: Book) {
        database.bookStorage.removeIf { it.id == updatedBook.id }
        database.bookStorage.add(updatedBook)
    }

    fun deleteBook(id: String?) {
        database.bookStorage.removeIf { it.id == id }
    }
}
