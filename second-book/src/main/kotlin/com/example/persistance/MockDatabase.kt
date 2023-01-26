package com.example.persistance

import com.example.core.Book

data class Database(
    val bookStorage: MutableList<Book> = mutableListOf(
        Book(
            "0000-00001",
            "Test Book",
            "Test Author",
            "Test Genre",
            "Test desc about very awesome test book",
            1.0
        )
    )
)
