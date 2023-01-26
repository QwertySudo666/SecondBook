package com.example.core

import java.util.*

class Book(
    val id: String,
    val name: String,
    val author: UUID,
    val genre: String, //should be enum
    val description: String,
    val price: Double
)
