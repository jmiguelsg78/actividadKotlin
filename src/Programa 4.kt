fun main() {
    val testCases = listOf(
        Pair(listOf("correr", "saltar", "correr", "saltar", "correr"), "_|_|_"),
        Pair(listOf("correr", "correr", "correr", "saltar", "correr"), "_|_|_"),
        Pair(listOf("correr", "correr", "saltar", "saltar", "correr"), "_|_|_"),
        Pair(listOf("correr", "correr", "saltar", "saltar", "correr"), "_|_|_|_"),
        Pair(listOf("correr", "saltar", "correr", "saltar"), "_|_|_"),
        Pair(listOf("correr", "saltar", "correr", "saltar", "correr", "saltar", "correr"), "_|_|_"),
        Pair(listOf("saltar", "saltar", "saltar", "saltar", "saltar"), "|||||"),
        Pair(listOf("saltar", "saltar", "saltar", "saltar", "saltar"), "||_||")
    )

    for ((acciones, pista) in testCases) {
        println("Acciones: $acciones")
        println("Pista original: $pista")
        val resultado = evaluarCarrera(acciones, pista)
        println("Pista final: ${resultado.first}")
        println("¿Superada?: ${resultado.second}\n")
    }
}

fun evaluarCarrera(acciones: List<String>, pista: String): Pair<String, Boolean> {
    if (acciones.size != pista.length) {
        return Pair("Error: las acciones y la pista no coinciden en longitud", false)
    }

    val pistaFinal = StringBuilder(pista)
    var carreraSuperada = true

    for ((i, tramo) in pista.withIndex()) {
        when (acciones[i]) {
            "correr" -> {
                when (tramo) {
                    '_' -> pistaFinal[i] = '_'
                    '|' -> {
                        pistaFinal[i] = '/'
                        carreraSuperada = false
                    }
                    else -> pistaFinal[i] = '?'
                }
            }
            "saltar" -> {
                when (tramo) {
                    '_' -> {
                        pistaFinal[i] = 'x'
                        carreraSuperada = false
                    }
                    '|' -> pistaFinal[i] = '|'
                    else -> pistaFinal[i] = '?'
                }
            }
            else -> {
                pistaFinal[i] = '?'
                carreraSuperada = false
            }
        }
    }

    return Pair(pistaFinal.toString(), carreraSuperada)
}
