package com.algomon.game

import java.util.Scanner

fun interval(player: Player, db:Connect){
    while(true) {
        println("Quer descansar, treinar ou aprender novo movimento? (d/t/n)")
        val choice = Scanner(System.`in`).nextLine()

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

            var enemyname = ""
            var enemyhp = 0
            var enemystamina = 0
            var enemyatk= 0
            var enemydef = 0
            var enemydodge = 0
            var enemyspeed = 0
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

            val enemy = Enemy(enemyname, enemyhp, enemystamina, enemymovements, enemyatk, enemydef, enemydodge, enemyspeed, player.level)
            if(battle(player, enemy, db) == 0) {
                println("Você perdeu a batalha. Mais cuidado na próxima")
                return
            } else{
                println("Bom jogo. Você venceu a batalha!")
            }

        } else{ //Caso escolhe obter movimento

            println("Qual movimento você deseja obter?")
            var movimentosDisponiveis: List<Int> = emptyList()
            var sql = "SELECT * FROM movements WHERE minlevel <= ${player.level};"
            var rs = db.query(sql)
            while(rs!!.next()){
               if(rs.getInt("id") in player.skills){
                   continue
               }
                movimentosDisponiveis = movimentosDisponiveis + rs.getInt("id")
                println("Id: ${rs.getInt("id")} Nome: ${rs.getString("name")} Preço: ${rs.getInt("preco")}")

            }
            println("Escolha seu movimento:")
            val choose = Scanner(System.`in`).nextInt()
            if(choose !in movimentosDisponiveis){
                println("Movimento não disponível.")
                continue
            }
            var preco = 0
            sql = "SELECT * FROM movements WHERE id = $choose;"
            rs = db.query(sql)
            while(rs!!.next()){
                preco = rs.getInt("preco")
            }
            if(preco <= player.carteira){
                player.skills += choose
                player.carteira -= preco
                println("Movimento adicionado à sua lista de movimentos")
            } else{
                println("Você não tem dinheiro para adquirir o movimento.")
            }

        }
    }
    return
}
