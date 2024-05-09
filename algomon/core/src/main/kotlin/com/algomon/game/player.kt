package com.algomon.game

import java.util.Scanner
import kotlin.math.min

class Player(name: String, hpbase: Int, staminabase: Int, skills: List<Int>, atkbase: Int, defbase: Int, dodgebase: Int,
                speedbase: Int, level: Int, var carteira: Int) : Character(name, hpbase, staminabase, skills, atkbase, defbase,
                dodgebase, speedbase, level, hpbase, staminabase, atkbase, defbase, dodgebase, speedbase){


    fun getMovements(db: Connect): List<String>{
        skills = skills.sorted()
        var movements: List<String> = emptyList()
        for (action in skills){
            val sql = "SELECT * FROM movements WHERE id = $action;"
            val rs = db.query(sql)
            while (rs!!.next()) {
                movements = movements + rs.getString("name")
            }
        }
        return movements
    }

    fun getMovementData(db: Connect, enemy: Character, choose: Int): List<Int>{
        var movementData: List<Int> = emptyList()
        val sql = "SELECT * FROM movements WHERE id = $choose;"
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

    fun getMovementName(db: Connect, choose: Int): String{
        val sql = "SELECT * FROM movements WHERE id = $choose;"
        val rs = db.query(sql)
        var movementName = ""
        while(rs!!.next()){
            movementName = rs.getString("name")
        }
        return movementName
    }

    fun getBaseAccuracy(db: Connect, choose: Int): Int{
        val sql = "SELECT * FROM movements WHERE id = $choose;"
        val rs = db.query(sql)
        var baseAccuracy = 0
        while(rs!!.next()){
            baseAccuracy = rs.getInt("baseaccuracy")
        }
        return baseAccuracy
    }

    fun useMovement(movementData: List<Int>, baseAccuracy: Int, enemy: Character): Int{ //Retorna 1 se o movimento foi bem-sucedido
                                                                                        //0 se errou e -1 se stamina é insuficiente
        val selfArray = movementData.slice(0..5)
        val enemyArray = movementData.slice(6..11)
        val zeroArray = listOf(0,0,0,0,0,0)
        if(stamina > selfArray[1]){ //If stamina is enough
            if(enemyArray == zeroArray){ //If movement doesn't change enemy stats
                val randomNum = kotlin.random.Random.nextInt(1, 101)
                if (randomNum < baseAccuracy) {
                    Change_Status(selfArray)
                    return 1
                } else {
                    return 0
                }
            } else { //If movement does change enemy stats
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

    fun ChooseMovement(enemy: Character, db: Connect): Int{

        //Get a list of movements the player can use and show it
        val movements: List<String> = getMovements(db)
        println("Movimentos disponíveis:")
        var cont: Int = 0
        for(i in skills.sorted()){
            println("Id: $i Nome: ${movements[cont]}")
        }

        println("Deseja atacar (1 se sim, 0 para fugir)")
        val run = Scanner(System.`in`).nextInt()
        if(run == 0)
            return 0
        println("Escolha seu movimento")
        val choose = Scanner(System.`in`).nextInt()

        //Access bank of data of chosen ID and get its data, name and base accuracy
        val movementData = getMovementData(db, enemy, choose)
        val movementName = getMovementName(db, choose)
        val baseAccuracy = getBaseAccuracy(db, choose)

        val success = useMovement(movementData, baseAccuracy, enemy)
        if(success == 1){
            println("Você usou $movementName")
        } else if(success == 0){
            println("Você errou o movimento")
        } else if(success == -1){
            println("Você não tem stamina suficiente para atacar")
        }

        return 1
    }

    fun ResetStats(){ //Reseta os status do player no início das batalhas
        hp = hpbase
        stamina = staminabase
        atk = atkbase
        def = defbase
        dodge = dodgebase
        speed = speedbase
    }
}
