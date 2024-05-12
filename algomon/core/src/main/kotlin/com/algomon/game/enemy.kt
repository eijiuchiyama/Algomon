package com.algomon.game

import kotlin.math.min

class Enemy(name: String, hpbase: Int, staminabase: Int, skills: List<Movement>, atkbase: Int, defbase: Int,
            dodgebase: Int, speedbase: Int, level: Int) : Character(name, hpbase, staminabase, skills, atkbase, defbase,
            dodgebase, speedbase, level, hpbase, staminabase, atkbase, defbase, dodgebase, speedbase){

    private fun getMovementData(enemy: Character, random: Int): List<Int>{
        var movementData: List<Int> = emptyList()
        movementData = movementData + skills[random].hpown
        movementData = movementData + skills[random].staminaown
        movementData = movementData + skills[random].atkown
        movementData = movementData + skills[random].defown
        movementData = movementData + skills[random].dodgeown
        movementData = movementData + skills[random].speedown
        movementData = movementData + min(skills[random].hpenemy - (this.atk - enemy.def), 0)
        movementData = movementData + skills[random].staminaenemy
        movementData = movementData + skills[random].atkenemy
        movementData = movementData + skills[random].defenemy
        movementData = movementData + skills[random].dodgeenemy
        movementData = movementData + skills[random].speedenemy
        return movementData
    }

    private fun useMovement(movementData: List<Int>, baseAccuracy: Int, enemy: Character): Int{ //Retorna 1 se movimento foi bem-sucedido, 0 se errou
                                                                                        // e -1 se stamina é insuficiente
        val selfArray = movementData.slice(0..5)
        val enemyArray = movementData.slice(6..11)
        val zeroArray = listOf(0,0,0,0,0,0)
        if(stamina > -selfArray[1]){ //If stamina is enough
            if(enemyArray == zeroArray) { //If movement doesn't change enemy stats
                val randomNum = kotlin.random.Random.nextInt(1, 101)
                if (randomNum < baseAccuracy) {
                    ChangeStatus(selfArray)
                    return 1
                }
                return 0

            } else{  //If movement does change enemy stats
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

    fun RandomMovement(enemy: Character){
        val random = kotlin.random.Random.nextInt(0, skills.size)

        //Access bank of data of ID choosed -> Data[12]
        val movementData = getMovementData(enemy, random)
        val movementName = skills[random].name
        val baseAccuracy = skills[random].baseaccuracy

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
