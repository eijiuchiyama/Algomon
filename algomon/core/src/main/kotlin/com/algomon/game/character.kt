package com.algomon.game

import kotlin.math.max

open class Character(var name: String, var hpbase: Int, var staminabase: Int, var skills: List<Movement>,
                     var atkbase: Int, var defbase: Int, var dodgebase: Int, var speedbase: Int, var level: Int,
                     var hp: Int, var stamina: Int, var atk: Int, var def: Int, var dodge: Int, var speed: Int){

    fun ShowStatus(){
        println(name)
        println("Level = $level, HP = $hp, Stamina = $stamina, Atk = $atk, Def = $def, " +
            "Dodge = $dodge, Speed = $speed")
    }

    fun ChangeStatus(change: List<Int>){
        hp      = max(0,hp     + change[0])
        stamina = max(0,stamina+ change[1])
        atk     = max(0,atk    + change[2])
        def     = max(0,def    + change[3])
        dodge   = max(0,dodge  + change[4])
        speed   = max(0,speed  + change[5])
    }

}
