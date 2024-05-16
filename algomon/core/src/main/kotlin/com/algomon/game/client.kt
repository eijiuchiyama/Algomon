package com.algomon.game

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


val client = HttpClient(CIO)

suspend fun request(type: String, column: String, table: String, condition: String): String {

    val response = client.get("http://localhost:8080/game?type=$type&column=$column&table=$table&condition=$condition")
    return response.body()

}

fun closeClient(){
    client.close()
}

