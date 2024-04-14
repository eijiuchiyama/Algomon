package algomon

import java.util.Scanner

class Player(name: String, HP: Int, Stamina: Int, Skill: List<Int>, Atk: Int, Def: Int, Dodge: Int, Speed: Int)
		   : Character(name, HP, Stamina, Skill, Atk, Def, Dodge, Speed){
    fun ChooseMovement(enemy: Character): Int{
        for (action in Skill){
        	//Println(action) Print the available actions
        }
        print("Escolha seu movimento: ")
        var choose = Scanner(System.`in`).nextInt()
        println()
        if(choose == -1){ //Se fugir retorna 0. Senão retorna 1
            if(kotlin.random.Random.nextInt(0, 2) == 0)
                return 0
            else {
                println("Não foi possível fugir")
                return 1
            }
        } else if(choose >= Skill.size){
            println("Não foi possível atacar")
            return 1
        }

        //Access bank of data of chosen ID-> Data[12]
        var Data1 = arrayOf(0, -20, 0, 0, 0, 0, -50, 0, 0, 0, 0, 0) //Attacks
        var Data2 = arrayOf(0, -30, 0, 0, 0, 0, -60, 0, 0, 0, 0, 0)
        var Data3 = arrayOf(0, -40, 0, 0, 0, 0, -70, 0, 0, 0, 0, 0)
        var Data = arrayOf(Data1, Data2, Data3)
        var self_array = Data[choose].slice(0..5)
        var enemy_array = Data[choose].slice(6..11)
        var enemy_name = enemy.name
        if(Stamina > self_array[1]){ //If stamina is enough
            println("$name ataca $enemy_name")
            Change_Status(self_array)
            enemy.Change_Status(enemy_array)
        } else{
            println("A stamina de $name não é suficiente")
        }
        return 1;
    }
}
