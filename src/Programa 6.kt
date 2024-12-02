fun main() {
    val agenda = Agenda()
    var opcion: Int

    do {
        println(
            """
            Menú:
            1. Añadir contacto
            2. Listar contactos
            3. Buscar contacto
            4. Existe contacto
            5. Eliminar contacto
            6. Contactos disponibles
            7. Agenda llena
            8. Salir
            Escribe una de las opciones
            """.trimIndent()
        )
        opcion = readLine()?.toIntOrNull() ?: -1

        when (opcion) {
            1 -> {
                println("Escribe un nombre:")
                val nombre = readLine().orEmpty()
                println("Escribe un teléfono:")
                val telefono = readLine().orEmpty()
                if (agenda.addContacto(Contacto(nombre, telefono))) {
                    println("Se ha añadido el contacto")
                } else {
                    println("No se puede añadir el contacto (ya existe o agenda llena)")
                }
            }
            2 -> {
                val contactos = agenda.listarContactos()
                if (contactos.isEmpty()) {
                    println("No hay contactos que mostrar")
                } else {
                    contactos.forEach { println("Nombre = ${it.nombre}, Teléfono = ${it.telefono}") }
                }
            }
            3 -> {
                println("Escribe un nombre:")
                val nombre = readLine().orEmpty()
                val contacto = agenda.buscarContacto(nombre)
                if (contacto != null) {
                    println("Su teléfono es ${contacto.telefono}")
                } else {
                    println("No se ha encontrado el contacto")
                }
            }
            4 -> {
                println("Escribe un nombre:")
                val nombre = readLine().orEmpty()
                if (agenda.existeContacto(nombre)) {
                    println("Existe contacto")
                } else {
                    println("No existe contacto")
                }
            }
            5 -> {
                println("Escribe un nombre:")
                val nombre = readLine().orEmpty()
                if (agenda.eliminarContacto(nombre)) {
                    println("Se ha eliminado el contacto")
                } else {
                    println("No se ha eliminado el contacto")
                }
            }
            6 -> {
                println("Hay ${agenda.contactosLibres()} contacto/s libre/s")
            }
            7 -> {
                if (agenda.estaLlena()) {
                    println("La agenda está llena")
                } else {
                    println("Aún se pueden meter contactos")
                }
            }
            8 -> {
                println("Saliendo del programa...")
            }
            else -> {
                println("Opción no válida. Intenta nuevamente.")
            }
        }
    } while (opcion != 8)
}

data class Contacto(val nombre: String, val telefono: String)

class Agenda(private val maxContactos: Int = 10) {
    private val contactos = mutableListOf<Contacto>()

    fun addContacto(contacto: Contacto): Boolean {
        if (estaLlena() || contactos.any { it.nombre == contacto.nombre }) return false
        contactos.add(contacto)
        return true
    }

    fun listarContactos(): List<Contacto> = contactos

    fun buscarContacto(nombre: String): Contacto? = contactos.find { it.nombre == nombre }

    fun existeContacto(nombre: String): Boolean = contactos.any { it.nombre == nombre }

    fun eliminarContacto(nombre: String): Boolean = contactos.removeIf { it.nombre == nombre }

    fun contactosLibres(): Int = maxContactos - contactos.size

    fun estaLlena(): Boolean = contactos.size >= maxContactos
}
