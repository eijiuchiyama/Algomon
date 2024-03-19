import kotlin.math.*
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

class Enemy(name: String, HP: Int, Stamina: Int, Skill: List<Int>, Atk: Int, Def: Int, Dodge: Int, Speed: Int)
		   : Character(name, HP, Stamina, Skill, Atk, Def, Dodge, Speed){
    fun RandomMovement(enemy: Character){
        var random_movement = kotlin.random.Random.nextInt(0, Skill.size)
        //access bank of data of ID choosed -> Data[12]
        var Data1 = arrayOf(0, -20, 0, 0, 0, 0, -50, 0, 0, 0, 0, 0) //Ataques
        var Data2 = arrayOf(0, -30, 0, 0, 0, 0, -60, 0, 0, 0, 0, 0)
        var Data3 = arrayOf(0, -40, 0, 0, 0, 0, -70, 0, 0, 0, 0, 0)
        var Data = arrayOf(Data1, Data2, Data3)
        var self_array = Data[random_movement].slice(0..5)
        var enemy_array = Data[random_movement].slice(6..11)
        
        if(stamina > self_array[1]){ //If stamina is enough
            println("$name ataca $enemy.name")
            Change_Status(self_array)
            enemy.Change_Status(enemy_array)
       } else{
            println("A stamina de $name não é suficiente para atacar")
       }
    }
}

class Player(name: String, HP: Int, Stamina: Int, Skill: List<Int>, Atk: Int, Def: Int, Dodge: Int, Speed: Int)
		   : Character(name, HP, Stamina, Skill, Atk, Def, Dodge, Speed){
    fun ChooseMovement(enemy:Character){
        for (action in Skill){
        	//Println(action) Print the available actions
        }
        	var choose = readLine()
    		//Access bank of data of ID choosed -> Data[12]
            var Data1 = arrayOf(0, -20, 0, 0, 0, 0, -50, 0, 0, 0, 0, 0) //Attacks
            var Data2 = arrayOf(0, -30, 0, 0, 0, 0, -60, 0, 0, 0, 0, 0)
            var Data3 = arrayOf(0, -40, 0, 0, 0, 0, -70, 0, 0, 0, 0, 0)
            var Data = arrayOf(Data1, Data2, Data3)
            var self_array = Data[choose].slice(0..5)
            var enemy_array = Data[choose].slice(6..11)
            if(stamina > self_array[1]){ //If stamina is enough
                println("$name ataca $enemy.name")
            	Change_Status(self_array)
            	enemy.Change_Status(enemy_array)
            } else{
                println("A stamina de $name não é suficiente")
            }
    }
}
           
fun game(player:Player, enemy:Enemy){
    var turn = 1;
    while(true){
        println("Turn $turn")
        player.Show_Status()
        enemy.Show_Status()
        if(player.Speed > enemy.Speed){
            player.ChooseMovement(enemy)
            if(player.HP==0 || enemy.HP==0) break;
            enemy.RandomMovement(player)
            if(player.HP==0 || enemy.HP==0) break;
        }
        else{
            enemy.RandomMovement(player)
            if(player.HP==0 || enemy.HP==0) break;
            player.ChooseMovement(enemy)
            if(player.HP==0 || enemy.HP==0) break;
        }
        println()
        turn++
    }
    if(player.HP==0) println("Game Over")
    else if(enemy.HP==0) println("You Win")
    else println("Draw")
}

fun main() {
	/**
	 *Movimento
     	1 - ID 
        2 - HP-P
    	3 - Stamina-P
    	4 - Atk-P
    	5 - Def-P
    	6 - Dodge-P
    	7 - Speed-P
        8 - HP-A
    	9 - Stamina-A
    	10 - Atk-A
    	11 - Def-A
    	12 - Dodge-A
    	13 - Speed-A
	*/
    
    /**
     * Ataque = Hacking
     * Regenerar HP e Stamina = take a nap
     * Aumentar própria Defesa = criptografar
     * Aumentar própria Ataque = Correção de Bug/
     * Aumentar próprio Speed = Compactar
     * Resetar os atributos próprios = Refactoring
     * Diminuir Defesa adversária = descriptografar
     * Diminuir Ataque adversária = 
     * Diminuir o Dodge adversária = Path-finding
     */
    var Jogador = Player("Test", 500,500,listOf(1,2,3),10,10,10,10)
    var ABC = Enemy("ABC", 500,500,listOf(1,2,3),50,50,50,50)
    game(Jogador,ABC)
}