package algomon

import kotlin.math.*
import java.util.Scanner

abstract class Random

open class Character(var name: String, var HP: Int, var Stamina: Int, var Skill: List<Int>, 
                     var Atk: Int, var Def: Int, var Dodge: Int, var Speed: Int){

    fun Show_Status(){
        println(name)
        println("HP = $HP, Stamina = $Stamina, Skill = $Skill, Atk = $Atk, Def = $Def, Dodge = $Dodge, Speed = $Speed")
    }

    fun Change_Status(change: List<Int>){
        HP      = max(0,HP     + change[0])
        Stamina = max(0,Stamina+ change[1])
        Atk     = max(0,Atk    + change[2])
        Def     = max(0,Def    + change[3])
        Dodge   = max(0,Dodge  + change[4])
        Speed   = max(0,Speed  + change[5])
    }
    
}