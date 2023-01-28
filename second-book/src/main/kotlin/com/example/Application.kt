package com.example

import com.example.persistance.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module, watchPaths = listOf("classes"))
        .start(wait = true)
}

fun Application.module() {
//    configureHTTP()
    DatabaseFactory.init()
    configureSerialization()
    configureRouting()
}
// To implement
// 1.book api
// 2.swagger
// 3.database
// 4.koin
//
// To learn/understand
//--------
// 1.coroutines
//
