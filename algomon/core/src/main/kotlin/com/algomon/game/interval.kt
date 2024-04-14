package algomon

import java.util.Scanner

fun interval(player: Player): Int{
    var sair = 0
    while(sair == 0) {
        var choice: String
        println("Quer descansar, treinar ou aprender novo movimento? (d/t/n)")
        choice = Scanner(System.`in`).nextLine()

        if(choice == "d" || choice == "D"){
            break
        } else if(choice == "t" || choice == "T") {
            //Escolhe o oponente aleatoriamente
            /*if(battle(player, enemy) == 0){
                return 0;
            }*/
        } else{

        }
    }
    return 1
}
