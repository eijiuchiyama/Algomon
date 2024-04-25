package algomon

import java.util.Scanner
import kotlin.math.*

class Player(name: String, hpbase: Int, staminabase: Int, skills: List<Int>, atkbase: Int, defbase: Int, dodgebase: Int,
                speedbase: Int, level: Int, var carteira: Int) : Character(name, hpbase, staminabase, skills, atkbase, defbase,
                dodgebase, speedbase, level, hpbase, staminabase, atkbase, defbase, dodgebase, speedbase){

    fun ChooseMovement(enemy: Character, db: Connect): Int{

        skills = skills.sorted()
        println("Movimentos disponíveis:")
        for (action in skills){
            val sql = "SELECT * FROM movements WHERE id = $action;"
            val rs = db.query(sql)
            while (rs!!.next()) {
                println("Id: ${rs.getInt("id")} Nome: ${rs.getString("name")}")
            }
        }
        print("Escolha seu movimento (ou -1 para fugir): ")
        val choose = Scanner(System.`in`).nextInt()
        println()
        if(choose == -1){ //Se fugir retorna 0. Senão retorna 1
            if(kotlin.random.Random.nextInt(0, 2) == 0) {
                println("Você deixou a batalha")
                return 0
            } else {
                println("Não foi possível fugir")
                return 1
            }
        } else if(choose >= skills.size){
            println("Não foi possível atacar")
            return 1
        }

        //Access bank of data of chosen ID-> Data[12]
        var movementData: List<Int> = emptyList()
        var movementName = ""
        var baseAccuracy = 0
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
            baseAccuracy = rs.getInt("baseaccuracy")
            movementName = rs.getString("name")
        }

        val selfArray = movementData.slice(0..5)
        val enemyArray = movementData.slice(6..11)
        val zeroArray = listOf(0,0,0,0,0,0)
        if(stamina > selfArray[1]){ //If stamina is enough
            if(enemyArray == zeroArray){ //If movement doesn't change enemy stats
                val randomNum = kotlin.random.Random.nextInt(1, 101)
                if (randomNum < baseAccuracy) {
                    println("Sua vez")
                    println("Utiliza $movementName")
                    Change_Status(selfArray)
                } else {
                    println("Movimento não foi bem sucedido")
                }
            } else { //If movement does change enemy stats
                val randomNum = kotlin.random.Random.nextInt(1, 101)
                if (randomNum < baseAccuracy - enemy.dodge) {
                    println("Sua vez")
                    println("Utiliza $movementName")
                    Change_Status(selfArray)
                    enemy.Change_Status(enemyArray)
                } else {
                    println("Movimento não foi bem sucedido")
                }
            }
        } else{
            println("A stamina não é suficiente para realizar o movimento")
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
