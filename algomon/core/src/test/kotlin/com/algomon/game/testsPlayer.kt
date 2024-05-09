package com.algomon.game

internal class TestPlayer{

    val player = Player("Pedrinho", 50, 70, listOf(2,1,0), 40, 40,5, 40, 3, 100)
    val db = Connect("PostgreSql", "localhost", "5432", "postgres", "postgres", "123369")



}
