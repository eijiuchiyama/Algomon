package algomon

import java.sql.DriverManager

fun getConnection(){

    val jdbcUrl = "jdbc:postgresql://localhost:5432/example"

    val connection = DriverManager
        .getConnection(jdbcUrl, "postgres", "postgres")

    if(connection.isValid(0))
        println("Conexão válida")

}