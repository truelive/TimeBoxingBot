package com.elrobit.timeboxingbot

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    // Starting point for a Ktor app:
    routing {
        get("/") {
            val client = HttpClient()
            val t1 = this@module.environment.config.property("ktor.app_token").getString()
            val response: HttpResponse = client.request("https://api.telegram.org/bot$t1/getMe") {
                method = HttpMethod.Get
            }
            call.respondText(response.call.response.bodyAsText())
        }
    }
    routing {
    }
}