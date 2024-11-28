fun main() {
    val expresiones = listOf(
        "{a + b [c] * (2x2)}}}}",
        "{ [ a * ( c + d ) ] - 5 }",
        "{ a * ( c + d ) ] - 5 }",
        "{a^4 + (((ax4)}",
        "{ ] a * ( c + d ) + ( 2 - 3 )[ - 5 }",
        "{{{{{{(}}}}}}",
        "(a"
    )

    for (expresion in expresiones) {
        println("Expresión: $expresion")
        if (estaBalanceada(expresion)) {
            println("Resultado: La expresión está balanceada.\n")
        } else {
            println("Resultado: La expresión NO está balanceada.\n")
        }
    }
}

fun estaBalanceada(expresion: String): Boolean {
    val pila = mutableListOf<Char>() // Pila para almacenar los delimitadores abiertos
    val pares = mapOf('}' to '{', ']' to '[', ')' to '(') // Mapeo de delimitadores cerrados con abiertos

    for (caracter in expresion) {
        when (caracter) {
            '{', '[', '(' -> pila.add(caracter) // Añadir delimitadores abiertos a la pila
            '}', ']', ')' -> {
                if (pila.isEmpty() || pila.removeAt(pila.size - 1) != pares[caracter]) {
                    return false // Si no hay un par correspondiente, no está balanceada
                }
            }
        }
    }

    return pila.isEmpty() // Si la pila está vacía, la expresión está balanceada
}
