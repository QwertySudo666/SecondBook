package com.example.core.adapter

import com.example.core.Book

interface BookRepositoryAdapter {
    suspend fun allBooks(): List<Book>
    suspend fun book(id: String): Book?
    suspend fun addNewBook(book: Book): Book?
    suspend fun editBook(updatedBook: Book): Book
    suspend fun deleteBook(id: String): Book
}
