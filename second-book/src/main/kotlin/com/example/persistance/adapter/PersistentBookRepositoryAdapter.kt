package com.example.persistance.adapter

import com.example.core.Book
import com.example.core.adapter.BookRepositoryAdapter
import com.example.persistance.BookEntity
import com.example.persistance.DatabaseFactory.dbQuery
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class PersistentBookRepositoryAdapter : BookRepositoryAdapter {
    override suspend fun allBooks(): List<Book> = dbQuery {
        BookEntity.selectAll().map(::resultRowToBook)
    }

    override suspend fun book(id: String): Book? = dbQuery {
        BookEntity
            .select { BookEntity.id eq id }
            .map(::resultRowToBook)
            .singleOrNull()
    }

    //Create fun bookToDBRow
    override suspend fun addNewBook(book: Book): Book? = dbQuery {
        val insertStatement = BookEntity.insert {
            it[BookEntity.id] = book.id
            it[BookEntity.name] = book.name
            it[BookEntity.author] = book.author
            it[BookEntity.genre] = book.genre
            it[BookEntity.description] = book.description
            it[BookEntity.price] = book.price
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToBook)
    }

    override suspend fun editBook(updatedBook: Book): Book = dbQuery {
        BookEntity.update({ BookEntity.id eq updatedBook.id }) {
            it[BookEntity.id] = updatedBook.id
            it[BookEntity.name] = updatedBook.name
            it[BookEntity.author] = updatedBook.author
            it[BookEntity.genre] = updatedBook.genre
            it[BookEntity.description] = updatedBook.description
            it[BookEntity.price] = updatedBook.price
        }

        BookEntity
            .select { BookEntity.id eq updatedBook.id }
            .map(::resultRowToBook)
            .singleOrNull()!!
    }

    override suspend fun deleteBook(id: String): Book = dbQuery {
        val deletedBook = BookEntity
            .select { BookEntity.id eq id }
            .map(::resultRowToBook)
            .singleOrNull()!!

        BookEntity.deleteWhere { BookEntity.id eq id }
        deletedBook
    }

    private fun resultRowToBook(row: ResultRow) = Book(
        id = row[BookEntity.id],
        name = row[BookEntity.name],
        author = row[BookEntity.author],
        genre = row[BookEntity.genre],
        description = row[BookEntity.description],
        price = row[BookEntity.price]
    )
}
