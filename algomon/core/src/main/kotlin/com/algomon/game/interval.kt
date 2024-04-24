package algomon

import java.util.Scanner

fun interval(player: Player, db:Connect){
    while(true) {
        println("Quer descansar, treinar ou aprender novo movimento? (d/t/n)")
        var choice = Scanner(System.`in`).nextLine()

        if(choice == "d" || choice == "D"){ //Caso escolhe descansar

            println("Você saiu do treinamento. Vamos então para o próximo turno.")
            break

        } else if(choice == "t" || choice == "T") { //Caso escolhe treinar

            //Escolhe o oponente de commonenemies aleatoriamente
            var enemiesId: List<Int> = emptyList()
            var sql = "SELECT * FROM specialenemies WHERE level = ${player.level};"
            var rs = db.query(sql)
            while(rs!!.next()){
                enemiesId = enemiesId + rs.getInt("id")
            }
            sql = "SELECT * FROM specialenemies WHERE id = ${enemiesId[kotlin.random.Random.nextInt(0, enemiesId.size)]}"
            rs = db.query(sql)

            var enemyname: String = ""
            var enemyhp: Int = 0
            var enemystamina: Int = 0
            var enemyatk: Int = 0
            var enemydef: Int = 0
            var enemydodge: Int = 0
            var enemyspeed: Int = 0
            var enemymovements: List<Int> = emptyList()
            while(rs!!.next()){
                enemyname = rs.getString("name")
                enemyhp = rs.getInt("basehp")
                enemystamina = rs.getInt("basestamina")
                enemyatk = rs.getInt("baseatk")
                enemydef = rs.getInt("basedef")
                enemydodge = rs.getInt("basedodge")
                enemyspeed = rs.getInt("basespeed")
            }

            sql = "SELECT id FROM movements WHERE minlevel <= ${player.level};"
            rs = db.query(sql)
            while(rs!!.next()){
                enemymovements = enemymovements + rs.getInt("id")
            }

            var enemy = Enemy(enemyname, enemyhp, enemystamina, enemymovements, enemyatk, enemydef, enemydodge, enemyspeed, player.level)
            if(battle(player, enemy, db) == 0) {
                println("Você perdeu a batalha. Mais cuidado na próxima")
                return
            } else{
                println("Parabéns. Você venceu a batalha!")
            }

        } else{

            println("Qual movimento você deseja obter?")
            var sql = "SELECT * FROM movements WHERE minlevel <= ${player.level};"
            var rs = db.query(sql)
            while(rs!!.next()){
               if(rs.getInt("id") in player.Skill){
                   continue
               }
                println("Id: ${rs.getInt("id")} Nome: ${rs.getString("name")} Preço: ${rs.getInt("preco")}")

            }
            println("Escolha seu movimento:")
            var choose = Scanner(System.`in`).nextInt()
            var preco: Int = 0
            sql = "SELECT * FROM movements WHERE id = $choose;"
            rs = db.query(sql)
            while(rs!!.next()){
                preco = rs.getInt("preco")
            }
            if(preco <= player.carteira){
                player.Skill = player.Skill + choose
                player.carteira = player.carteira - preco
                println("Movimento adicionado à sua lista de movimentos")
            } else{
                println("Você não tem dinheiro para adquirir o movimento.")
            }

        }
    }
    return
}
