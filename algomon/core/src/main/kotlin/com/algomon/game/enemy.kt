package algomon

class Enemy(name: String, HP: Int, Stamina: Int, Skill: List<Int>, Atk: Int, Def: Int, Dodge: Int, Speed: Int, level: Int)
		   : Character(name, HP, Stamina, Skill, Atk, Def, Dodge, Speed, level){
    fun RandomMovement(enemy: Character, db: Connect){
        var random_movement = kotlin.random.Random.nextInt(0, Skill.size)

        //Access bank of data of ID choosed -> Data[12]
        var movementData: List<Int> = emptyList()
        var movementName: String = ""
        var baseAccuracy: Int = 0
        var sql = "SELECT * FROM movements WHERE id = ${Skill[random_movement]};"
        var rs = db.query(sql)
        while(rs!!.next()){
            movementData = movementData + rs.getInt("hpown")
            movementData = movementData + rs.getInt("staminaown")
            movementData = movementData + rs.getInt("atkown")
            movementData = movementData + rs.getInt("defown")
            movementData = movementData + rs.getInt("dodgeown")
            movementData = movementData + rs.getInt("speedown")
            movementData = movementData + rs.getInt("hpenemy")
            movementData = movementData + rs.getInt("staminaenemy")
            movementData = movementData + rs.getInt("atkenemy")
            movementData = movementData + rs.getInt("defenemy")
            movementData = movementData + rs.getInt("dodgeenemy")
            movementData = movementData + rs.getInt("speedenemy")
            baseAccuracy = rs.getInt("baseaccuracy")
            movementName = rs.getString("name")
        }

        var self_array = movementData.slice(0..5)
        var enemy_array = movementData.slice(6..11)
        if(Stamina > self_array[1]){ //If stamina is enough
            var randomNum = kotlin.random.Random.nextInt(1, 101)
            if(randomNum >= baseAccuracy) {
                println("Vez de $name")
                println("Ataca com $movementName")
                Change_Status(self_array)
                enemy.Change_Status(enemy_array)
            } else{
                println("Movimento não foi bem sucedido")
            }
       } else{
            println("A stamina não é suficiente")
       }
    }
}
