fun main() {
    val matrices = arrayOf(
        arrayOf(arrayOf("X", "O", "X"), arrayOf("O", "X", "O"), arrayOf("O", "O", "X")),
        arrayOf(arrayOf("", "O", "X"), arrayOf("", "X", "O"), arrayOf("", "O", "X")),
        arrayOf(arrayOf("O", "O", "O"), arrayOf("O", "X", "X"), arrayOf("O", "X", "X")),
        arrayOf(arrayOf("X", "O", "X"), arrayOf("X", "X", "O"), arrayOf("X", "X", "X"))
    )

    val resultados = matrices.map { analizarMatriz(it) }

    // Imprimir cada resultado en una línea distinta
    for (resultado in resultados) {
        println(resultado)
    }
}

fun analizarMatriz(matriz: Array<Array<String>>): String {
    var contadorX = 0
    var contadorO = 0
    var espaciosVacios = 0

    // Contar "X", "O" y espacios vacíos
    for (fila in matriz) {
        for (celda in fila) {
            when (celda) {
                "X" -> contadorX++
                "O" -> contadorO++
                "" -> espaciosVacios++
            }
        }
    }

    // Comprobar si la proporción es correcta
    if (contadorX > 5 || contadorO > 5 || (contadorX + contadorO + espaciosVacios) > 9) {
        return "NULO"
    }

    // Comprobar las condiciones de victoria
    val filas = matriz
    val columnas = Array(3) { Array(3) { "" } }
    val diagonales = arrayOf(arrayOf(matriz[0][0], matriz[1][1], matriz[2][2]),
        arrayOf(matriz[0][2], matriz[1][1], matriz[2][0]))

    for (i in 0..2) {
        for (j in 0..2) {
            columnas[j][i] = matriz[i][j]
        }
    }

    // Función para verificar si hay un ganador
    fun hayGanador(linea: Array<String>): String? {
        return when {
            linea.all { it == "X" } -> "X"
            linea.all { it == "O" } -> "O"
            else -> null
        }
    }

    val ganadores = mutableSetOf<String>()

    for (linea in filas + columnas + diagonales) {
        hayGanador(linea)?.let { ganadores.add(it) }
    }

    // Verificar si hay múltiples ganadores
    if (ganadores.size > 1) {
        return "NULO"
    }

    // Determinar el resultado
    return when {
        "X" in ganadores -> "X"
        "O" in ganadores -> "O"
        contadorX + contadorO + espaciosVacios == 9 -> "EMPATE"
        else -> "NULO"
    }
}