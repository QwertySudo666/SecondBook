package com.example.core.facades

import com.example.core.Book
import com.example.core.adapter.BookRepositoryAdapter
import com.example.persistance.Database
import com.example.persistance.adapter.PersistentBookRepositoryAdapter
import kotlinx.coroutines.runBlocking

class BookFacade {
    private val database = Database()
    private val bookRepositoryAdapter: BookRepositoryAdapter = PersistentBookRepositoryAdapter().apply {
        runBlocking {
            if (allBooks().isEmpty()) {
                addNewBook(Book("T", "T", "T", "T", "T", 0.0))
            }
        }
    }

    suspend fun fetchBooks(): List<Book> {
        return bookRepositoryAdapter.allBooks()
    }

    suspend fun fetchBook(id: String): Book? {
        return bookRepositoryAdapter.book(id)
    }

    suspend fun saveBook(requestBook: Book) {
        bookRepositoryAdapter.addNewBook(requestBook)
    }

    suspend fun updateBook(updatedBook: Book) {
        bookRepositoryAdapter.editBook(updatedBook)
    }

    suspend fun deleteBook(id: String) {
        bookRepositoryAdapter.deleteBook(id)
    }
}
