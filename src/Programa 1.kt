fun main() {
    val rows = Array(10) { StringBuilder() } // 10 filas para almacenar las columnas
    for (i in 1..100) {
        val value = when {
            i % 3 == 0 && i % 5 == 0 -> "triqui"
            i % 3 == 0 -> "tri"
            i % 5 == 0 -> "qui"
            else -> i.toString()
        }
        val row = (i - 1) % 10 // Determina a qué fila pertenece este número
        rows[row].append(value.padEnd(6)) // Ajusta el ancho para columnas uniformes
    }

    // Imprime las filas
    for (row in rows) {
        println(row.toString())
    }
}
