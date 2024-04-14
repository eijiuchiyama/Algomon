package algomon

class Enemy(name: String, HP: Int, Stamina: Int, Skill: List<Int>, Atk: Int, Def: Int, Dodge: Int, Speed: Int)
		   : Character(name, HP, Stamina, Skill, Atk, Def, Dodge, Speed){
    fun RandomMovement(enemy: Character){
        var random_movement = kotlin.random.Random.nextInt(0, Skill.size)
        //Access bank of data of ID choosed -> Data[12]
        var Data1 = arrayOf(0, -20, 0, 0, 0, 0, -50, 0, 0, 0, 0, 0) //Attacks
        var Data2 = arrayOf(0, -30, 0, 0, 0, 0, -60, 0, 0, 0, 0, 0)
        var Data3 = arrayOf(0, -40, 0, 0, 0, 0, -70, 0, 0, 0, 0, 0)
        var Data = arrayOf(Data1, Data2, Data3)
        var self_array = Data[random_movement].slice(0..5)
        var enemy_array = Data[random_movement].slice(6..11)
        var enemy_name = enemy.name
        if(Stamina > self_array[1]){ //If stamina is enough
            println("$name ataca $enemy_name")
            Change_Status(self_array)
            enemy.Change_Status(enemy_array)
       } else{
            println("A stamina de $name não é suficiente para atacar")
       }
    }
}
