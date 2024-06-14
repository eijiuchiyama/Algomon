package com.algomon.game

import java.util.Scanner
import kotlin.math.min

class Player(name: String, hpbase: Int, staminabase: Int, skills: MutableList<Movement>, atkbase: Int, defbase: Int, dodgebase: Int,
                speedbase: Int, level: Int, var carteira: Int) : Character(name, hpbase, staminabase, skills, atkbase, defbase,
                dodgebase, speedbase, level, hpbase, staminabase, atkbase, defbase, dodgebase, speedbase){


    fun printMovements(){
        for (action in skills){
            println("${action.id} ${action.name}")
        }
    }

    fun getMovementData(enemy: Character, choose: Int): List<Int>{
        var movementData: List<Int> = emptyList()
        for(action in skills){
            if(action.id == choose){
                movementData = movementData + action.hpown
                movementData = movementData + action.staminaown
                movementData = movementData + action.atkown
                movementData = movementData + action.defown
                movementData = movementData + action.dodgeown
                movementData = movementData + action.speedown
                movementData = movementData + min(action.hpenemy - (this.atk - enemy.def), 0)
                movementData = movementData + action.staminaenemy
                movementData = movementData + action.atkenemy
                movementData = movementData + action.defenemy
                movementData = movementData + action.dodgeenemy
                movementData = movementData + action.speedenemy
                return movementData
            }
        }
        return emptyList()
    }

    fun getMovementName(choose: Int): String{
        for(action in skills){
            if(action.id == choose)
                return action.name
        }
        return ""
    }

    fun getBaseAccuracy(choose: Int): Int{
        for(action in skills){
            if(action.id == choose)
                return action.baseaccuracy
        }
        return 0
    }

    fun useMovement(movementData: List<Int>, baseAccuracy: Int, enemy: Character): Int{ //Retorna 1 se o movimento foi bem-sucedido
                                                                                        //0 se errou e -1 se stamina é insuficiente
        val selfArray = movementData.slice(0..5)
        val enemyArray = movementData.slice(6..11)
        val zeroArray = listOf(0,0,0,0,0,0)
        if(stamina > - selfArray[1]){ //If stamina is enough
            if(enemyArray == zeroArray){ //If movement doesn't change enemy stats
                val randomNum = kotlin.random.Random.nextInt(1, 101)
                if (randomNum < baseAccuracy) {
                    ChangeStatus(selfArray)
                    return 1
                }
                return 0

            } else { //If movement does change enemy stats
                val randomNum = kotlin.random.Random.nextInt(1, 101)
                if (randomNum < baseAccuracy - enemy.dodge) {
                    ChangeStatus(selfArray)
                    enemy.ChangeStatus(enemyArray)
                    return 1
                }
                return 0

            }
        } else{
            return -1
        }
    }

    fun ChooseMovement(enemy: Character): Int{

        //Get a list of movements the player can use and show it
        println("Movimentos disponíveis:")
        printMovements()

        println("Deseja atacar (1 se sim, 0 para fugir)")
        val run = Scanner(System.`in`).nextInt()
        if(run == 0)
            return 0
        println("Escolha seu movimento")
        val choose = Scanner(System.`in`).nextInt()

        //Access bank of data of chosen ID and get its data, name and base accuracy
        val movementData = getMovementData(enemy, choose)
        val movementName = getMovementName(choose)
        val baseAccuracy = getBaseAccuracy(choose)

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
