package com.algomon.game

import kotlin.math.min

class Enemy(name: String, hpbase: Int, staminabase: Int, skills: List<Int>, atkbase: Int, defbase: Int,
            dodgebase: Int, speedbase: Int, level: Int) : Character(name, hpbase, staminabase, skills, atkbase, defbase,
            dodgebase, speedbase, level, hpbase, staminabase, atkbase, defbase, dodgebase, speedbase){

    fun getMovementData(db: Connect, enemy: Character, random_movement: Int): List<Int>{
        var movementData: List<Int> = emptyList()
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
        }
        return movementData
    }

    fun getMovementName(db: Connect, random_movement: Int): String{
        var movementName = ""
        val sql = "SELECT * FROM movements WHERE id = ${skills[random_movement]};"
        val rs = db.query(sql)
        while(rs!!.next()) {
            movementName = rs.getString("name")
        }
        return movementName
    }

    fun getBaseAccuracy(db: Connect, random_movement: Int): Int{
        var baseAccuracy = 0
        val sql = "SELECT * FROM movements WHERE id = ${skills[random_movement]};"
        val rs = db.query(sql)
        while(rs!!.next()) {
            baseAccuracy = rs.getInt("baseaccuracy")
        }
        return baseAccuracy
    }

    fun useMovement(movementData: List<Int>, baseAccuracy: Int, enemy: Character): Int{ //Retorna 1 se movimento foi bem-sucedido, 0 se errou
                                                                                        // e -1 se stamina é insuficiente
        val selfArray = movementData.slice(0..5)
        val enemyArray = movementData.slice(6..11)
        val zeroArray = listOf(0,0,0,0,0,0)
        if(stamina > selfArray[1]){ //If stamina is enough
            if(enemyArray == zeroArray) { //If movement doesn't change enemy stats
                val randomNum = kotlin.random.Random.nextInt(1, 101)
                if (randomNum < baseAccuracy) {
                    Change_Status(selfArray)
                    return 1
                } else {
                    return 0
                }
            } else{  //If movement does change enemy stats
                val randomNum = kotlin.random.Random.nextInt(1, 101)
                if (randomNum < baseAccuracy - enemy.dodge) {
                    Change_Status(selfArray)
                    enemy.Change_Status(enemyArray)
                    return 1
                } else {
                    return 0
                }
            }
        } else{
            return -1
        }
    }

    fun RandomMovement(enemy: Character, db: Connect){
        val random_movement = kotlin.random.Random.nextInt(0, skills.size)

        //Access bank of data of ID choosed -> Data[12]
        var movementData = getMovementData(db, enemy, random_movement)
        var movementName = getMovementName(db, random_movement)
        var baseAccuracy = getBaseAccuracy(db, random_movement)

        val success = useMovement(movementData, baseAccuracy, enemy)
        if(success == 1){
            println("O inimigo usou $movementName")
        } else if(success == 0){
            println("O inimigo errou o movimento")
        } else if(success == -1){
            println("O inimigo não tem stamina suficiente para atacar")
        }
    }
}
