package com.algomon.game

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

fun main() {
    val db = databaseConnect()
    embeddedServer(Netty, port = 8080, host = "172.19.229.199"){
        routing {
            get("/game") {
                val type = call.request.queryParameters["type"]
                val column = call.request.queryParameters["column"]
                val table = call.request.queryParameters["table"]
                val condition = call.request.queryParameters["condition"]

                //println("$type $column $table $condition")

                if(type == "ids"){ //Pegar IDs

                    var ids: List<Int> = emptyList()
                    val rs = db.query("SELECT $column FROM $table WHERE $condition;")
                    while(rs!!.next()){
                        ids = ids + rs.getInt("id")
                    }
                    call.respond(Json.encodeToString(ids))

                } else if(type == "name"){ //Pegar nome

                    var name = ""
                    val rs = db.query("SELECT $column FROM $table WHERE $condition;")
                    while(rs!!.next()){
                        name = rs.getString("name")
                    }
                    call.respond(Json.encodeToString(name))

                } else if(type == "names"){ //Pegar nomes

                    var names: List<String> = emptyList()
                    val rs = db.query("SELECT $column FROM $table WHERE $condition;")
                    while(rs!!.next()){
                        names = names + rs.getString("name")
                    }
                    call.respond(Json.encodeToString(names))

                } else if(type == "movementdata"){ //Pega dados de um único movimento

                    var movement = Movement(0, "", 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0,0, 0, 0, 0, 0)
                    val rs = db.query("SELECT $column FROM $table WHERE $condition;")
                    while(rs!!.next()){
                        movement = Movement(rs.getInt("id"), rs.getString("name"), rs.getInt("hpown"), rs.getInt("staminaown"),
                            rs.getInt("atkown"), rs.getInt("defown"), rs.getInt("dodgeown"), rs.getInt("speedown"), rs.getInt("hpenemy"), rs.getInt("staminaenemy"),
                            rs.getInt("atkenemy"), rs.getInt("defenemy"), rs.getInt("dodgeenemy"), rs.getInt("speedenemy"), rs.getInt("minlevel"),
                            rs.getInt("baseaccuracy"), rs.getInt("price"))
                    }
                    call.respond(Json.encodeToString(movement))

                } else if(type == "movementsdata"){ //Pegar dados de movimento

                    var movements: List<Movement> = emptyList()
                    val rs = db.query("SELECT $column FROM $table WHERE $condition;")
                    while(rs!!.next()){
                        movements = movements + Movement(rs.getInt("id"), rs.getString("name"), rs.getInt("hpown"), rs.getInt("staminaown"),
                            rs.getInt("atkown"), rs.getInt("defown"), rs.getInt("dodgeown"), rs.getInt("speedown"), rs.getInt("hpenemy"), rs.getInt("staminaenemy"),
                            rs.getInt("atkenemy"), rs.getInt("defenemy"), rs.getInt("dodgeenemy"), rs.getInt("speedenemy"), rs.getInt("minlevel"),
                            rs.getInt("baseaccuracy"), rs.getInt("price"))
                    }
                    call.respond(Json.encodeToString(movements))

                } else if(type == "playerdata"){ //Pegar dados do player

                    var playerData: List<Int> = emptyList()
                    val rs = db.query("SELECT $column FROM $table WHERE $condition;")
                    while(rs!!.next()){
                        playerData = playerData + rs.getInt("basehp")
                        playerData = playerData + rs.getInt("basestamina")
                        playerData = playerData + rs.getInt("baseatk")
                        playerData = playerData + rs.getInt("basedef")
                        playerData = playerData + rs.getInt("basedodge")
                        playerData = playerData + rs.getInt("basespeed")
                    }
                    call.respond(Json.encodeToString(playerData))

                } else if(type == "enemydata"){ //Pegar dados do enemy

                    var enemyData: List<Int> = emptyList()
                    val rs = db.query("SELECT $column FROM $table WHERE $condition;")
                    while(rs!!.next()){
                        enemyData = enemyData + rs.getInt("basehp")
                        enemyData = enemyData + rs.getInt("basestamina")
                        enemyData = enemyData + rs.getInt("baseatk")
                        enemyData = enemyData + rs.getInt("basedef")
                        enemyData = enemyData + rs.getInt("basedodge")
                        enemyData = enemyData + rs.getInt("basespeed")
                        enemyData = enemyData + rs.getInt("level")
                    }
                    call.respond(Json.encodeToString(enemyData))

                } else{
                    call.respondText("Opção não disponível")
                }

            }
        }
    }.start(wait = true)
}
