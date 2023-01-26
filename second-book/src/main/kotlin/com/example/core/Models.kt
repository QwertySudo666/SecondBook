package com.example.core

import kotlinx.serialization.Serializable

@Serializable
class Book(
    val id: String,
    val name: String,
    val author: String,
    val genre: String, //should be enum
    val description: String,
    val price: Double
)
