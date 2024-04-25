package algomon

import kotlin.math.*

open class Character(var name: String, var hpbase: Int, var staminabase: Int, var skills: List<Int>,
                     var atkbase: Int, var defbase: Int, var dodgebase: Int, var speedbase: Int, var level: Int,
                     var hp: Int, var stamina: Int, var atk: Int, var def: Int, var dodge: Int, var speed: Int){

    fun Show_Status(){
        println(name)
        println("Level = $level, HP = $hp, Stamina = $stamina, Skill = $skills, Atk = $atk, Def = $def, " +
            "Dodge = $dodge, Speed = $speed")
    }

    fun Change_Status(change: List<Int>){
        hp      = max(0,hp     + change[0])
        stamina = max(0,stamina+ change[1])
        atk     = max(0,atk    + change[2])
        def     = max(0,def    + change[3])
        dodge   = max(0,dodge  + change[4])
        speed   = max(0,speed  + change[5])
    }

}
