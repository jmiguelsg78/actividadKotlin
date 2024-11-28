fun main() {
    println("Introduce la primera palabra:")
    val palabra1 = readLine()?.trim()?.lowercase() ?: ""

    println("Introduce la segunda palabra:")
    val palabra2 = readLine()?.trim()?.lowercase() ?: ""

    if (esAnagrama(palabra1, palabra2)) {
        println("Las palabras \"$palabra1\" y \"$palabra2\" son anagramas.")
    } else {
        println("Las palabras \"$palabra1\" y \"$palabra2\" NO son anagramas.")
    }
}

fun esAnagrama(palabra1: String, palabra2: String): Boolean {
    // Verificar que no sean idénticas y tengan la misma longitud
    if (palabra1 == palabra2 || palabra1.length != palabra2.length) {
        return false
    }
    // Ordenar los caracteres de ambas palabras y compararlas
    return palabra1.toCharArray().sorted() == palabra2.toCharArray().sorted()
}
