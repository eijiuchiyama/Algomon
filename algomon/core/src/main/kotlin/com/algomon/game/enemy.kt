package com.algomon.game

import kotlin.math.*

class Enemy(name: String, hpbase: Int, staminabase: Int, skills: List<Int>, atkbase: Int, defbase: Int,
            dodgebase: Int, speedbase: Int, level: Int) : Character(name, hpbase, staminabase, skills, atkbase, defbase,
            dodgebase, speedbase, level, hpbase, staminabase, atkbase, defbase, dodgebase, speedbase){
    fun RandomMovement(enemy: Character, db: Connect){
        val random_movement = kotlin.random.Random.nextInt(0, skills.size)

        //Access bank of data of ID choosed -> Data[12]
        var movementData: List<Int> = emptyList()
        var movementName = ""
        var baseAccuracy = 0
        val sql = "SELECT * FROM movements WHERE id = ${skills[random_movement]};"
        val rs = db.query(sql)
        while(rs!!.next()){
            movementData = movementData + rs.getInt("hpown")
            movementData = movementData + rs.getInt("staminaown")
            movementData = movementData + rs.getInt("atkown")
            movementData = movementData + rs.getInt("defown")
            movementData = movementData + rs.getInt("dodgeown")
            movementData = movementData + rs.getInt("speedown")
            movementData = movementData + min(rs.getInt("hpenemy") - (this.atk - enemy.def), 0)
            movementData = movementData + rs.getInt("staminaenemy")
            movementData = movementData + rs.getInt("atkenemy")
            movementData = movementData + rs.getInt("defenemy")
            movementData = movementData + rs.getInt("dodgeenemy")
            movementData = movementData + rs.getInt("speedenemy")
            baseAccuracy = rs.getInt("baseaccuracy")
            movementName = rs.getString("name")
        }

        val selfArray = movementData.slice(0..5)
        val enemyArray = movementData.slice(6..11)
        val zeroArray = listOf(0,0,0,0,0,0)
        if(stamina > selfArray[1]){ //If stamina is enough
            if(enemyArray == zeroArray) { //If movement doesn't change enemy stats
                val randomNum = kotlin.random.Random.nextInt(1, 101)
                if (randomNum < baseAccuracy) {
                    println("Vez de $name")
                    println("Utiliza $movementName")
                    Change_Status(selfArray)
                } else {
                    println("Movimento não foi bem sucedido")
                }
            } else{  //If movement does change enemy stats
                val randomNum = kotlin.random.Random.nextInt(1, 101)
                if (randomNum < baseAccuracy - enemy.dodge) {
                    println("Vez de $name")
                    println("Utiliza $movementName")
                    Change_Status(selfArray)
                    enemy.Change_Status(enemyArray)
                } else {
                    println("Movimento não foi bem sucedido")
                }
            }
       } else{
            println("A stamina não é suficiente para realizar o movimento")
       }
    }
}
