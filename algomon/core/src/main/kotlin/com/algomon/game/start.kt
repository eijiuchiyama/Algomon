package algomon

import java.util.Scanner

fun start(){
    println("Parabens! Você acaba de se inscrever no nosso torneio!")
    println("Eis as regras:")
    println("   Você deve vencer todas as batalhas para vencer o torneio.")
    println("   Ataque seu adversário até que o HP caia dele para 0. Busque conservar sua stamina.")
    println("   Entre cada batalha você pode descansar, treinar ou aprender novos movimentos.")
    println("   Quando estiver pronto, iniciaremos a batalha seguinte")
    println("Tome cuidado para não perder seu HP e ser eliminado. O próximo torneio é só ano que vem!")
    println("Acho que é tudo. Boa sorte.")

    var startBattle = "n"
    while(startBattle == "n" || startBattle == "N"){
        println("Começar primeira batalha? (s/n)")
        startBattle = Scanner(System.`in`).nextLine()
    }

}
