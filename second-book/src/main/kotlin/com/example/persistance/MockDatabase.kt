package com.example.persistance

import com.example.core.Book

data class Database(
    val bookStorage: MutableList<Book> = mutableListOf()
)
