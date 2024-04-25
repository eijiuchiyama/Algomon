package algomon

import java.util.Scanner

fun start(): Int{
    println("Parabens! Você acaba de se inscrever no nosso torneio!")
    println("Se você quer se tornar um programador experiente, está no caminho certo.")
    println("Você terá que competir com outros utilizando código.")
    println("Eis as regras:")
    println("   Você deve vencer todas as batalhas para vencer o torneio.")
    println("   Ataque seu adversário até que o HP caia dele para 0. Busque conservar sua stamina.")
    println("   Entre cada batalha você pode descansar, treinar ou aprender novos movimentos. Super recomendado.")
    println("   Quando estiver pronto, iniciaremos a batalha seguinte.")
    println("Tome cuidado para não perder seu HP e ser eliminado. O próximo torneio é só ano que vem!")
    println("")
    println("Acho que é tudo. Boa sorte.")

    var startGame = "n"
    var quitGame: String
    while(startGame == "n" || startGame == "N") {
        println("Começar primeira batalha? (s/n)")
        startGame = Scanner(System.`in`).nextLine()
        if(startGame == "n" || startGame == "N"){
            println("Vai desistir? (s/n)")
            quitGame = Scanner(System.`in`).nextLine()
            if(quitGame == "s" || quitGame == "S"){
                println("Que pena! Esperamos que se inscreva novamente ano que vem.")
                return 0
            }
        }
    }
    return 1

}
