package algomon

import java.util.Scanner
import kotlin.math.*

class Player(name: String, HP: Int, Stamina: Int, Skill: List<Int>, Atk: Int, Def: Int, Dodge: Int, Speed: Int, level: Int,
             var carteira: Int)
		   : Character(name, HP, Stamina, Skill, Atk, Def, Dodge, Speed, level){
    fun ChooseMovement(enemy: Character, db: Connect): Int{

        Skill = Skill.sorted()
        println("Movimentos disponíveis:")
        for (action in Skill){
            var sql = "SELECT * FROM movements WHERE id = $action;"
            var rs = db.query(sql)
            while (rs!!.next()) {
                println("Id: ${rs.getInt("id")} Nome: ${rs.getString("name")}")
            }
        }
        print("Escolha seu movimento (ou -1 para fugir): ")
        var choose = Scanner(System.`in`).nextInt()
        println()
        if(choose == -1){ //Se fugir retorna 0. Senão retorna 1
            if(kotlin.random.Random.nextInt(0, 2) == 0) {
                println("Você deixou a batalha")
                return 0
            } else {
                println("Não foi possível fugir")
                return 1
            }
        } else if(choose >= Skill.size){
            println("Não foi possível atacar")
            return 1
        }

        //Access bank of data of chosen ID-> Data[12]
        var movementData: List<Int> = emptyList()
        var movementName: String = ""
        var baseAccuracy: Int = 0
        var sql = "SELECT * FROM movements WHERE id = $choose;"
        var rs = db.query(sql)
        while(rs!!.next()){
            movementData = movementData + rs.getInt("hpown")
            movementData = movementData + rs.getInt("staminaown")
            movementData = movementData + rs.getInt("atkown")
            movementData = movementData + rs.getInt("defown")
            movementData = movementData + rs.getInt("dodgeown")
            movementData = movementData + rs.getInt("speedown")
            movementData = movementData + min(rs.getInt("hpenemy") - (this.Atk - enemy.Def), 0)
            movementData = movementData + rs.getInt("staminaenemy")
            movementData = movementData + rs.getInt("atkenemy")
            movementData = movementData + rs.getInt("defenemy")
            movementData = movementData + rs.getInt("dodgeenemy")
            movementData = movementData + rs.getInt("speedenemy")
            baseAccuracy = rs.getInt("baseaccuracy")
            movementName = rs.getString("name")
        }

        println(movementData)
        var self_array = movementData.slice(0..5)
        var enemy_array = movementData.slice(6..11)
        val zero_array = listOf(0,0,0,0,0,0)
        if(Stamina > self_array[1]){ //If stamina is enough
            if(enemy_array == zero_array){ //If movement doesn't change enemy stats
                var randomNum = kotlin.random.Random.nextInt(1, 101)
                if (randomNum < baseAccuracy) {
                    println("Vez de $name")
                    println("Utiliza $movementName")
                    Change_Status(self_array)
                } else {
                    println("Movimento não foi bem sucedido")
                }
            } else { //If movement does change enemy stats
                var randomNum = kotlin.random.Random.nextInt(1, 101)
                if (randomNum < baseAccuracy - enemy.Dodge) {
                    println("Vez de $name")
                    println("Utiliza $movementName")
                    Change_Status(self_array)
                    enemy.Change_Status(enemy_array)
                } else {
                    println("Movimento não foi bem sucedido")
                }
            }
        } else{
            println("A stamina não é suficiente para realizar o movimento")
        }
        return 1;
    }
}
