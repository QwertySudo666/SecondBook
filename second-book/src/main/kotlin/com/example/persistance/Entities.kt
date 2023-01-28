package com.example.persistance

import org.jetbrains.exposed.sql.Table

object BookEntity : Table() {
    val id = varchar("id", 64)
    val name = varchar("name", 255)
    val author = varchar("author", 64)
    val genre = varchar("genre", 64) //should be enum
    val description = varchar("description", 255)
    val price = double("price")

    override val primaryKey = PrimaryKey(id)
}
