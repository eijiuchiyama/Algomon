package com.algomon.game

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json


val client = HttpClient(CIO)

suspend fun sendRequest(type: String, column: String, table: String, condition: String): HttpResponse {

    val response: HttpResponse = client.get("http://localhost:8080/game?type=$type&column=$column&table=$table&condition=$condition")
    return response

}

suspend fun main(){
    var response = sendRequest("name", "name", "commonenemies", "id=0")
    val body: String = response.body()
    println(body)
    response = sendRequest("ids", "id", "commonenemies", "level=0")
    val body2: String = response.body()
    val list: List<Int> = Json.decodeFromString(body2)
    println(list)
    closeClient()
}

fun closeClient(){
    client.close()
}

