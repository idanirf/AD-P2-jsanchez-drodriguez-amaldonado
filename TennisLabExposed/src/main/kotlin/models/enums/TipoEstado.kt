package models.enums

/**
 * Tipo estado: Es una clase Enum
 *
 * @property est
 * @constructor Create empty Tipo estado
 */
enum class TipoEstado(val est: String) {
    RECIBIDO("RECIBIDO"),
    EN_PROCESO("EN PROCESO"),
    TERMINADO("TERMINADO");

    companion object {
        fun from(estado: String): TipoEstado {
            return when (estado.uppercase()) {
                "RECIBIDO" -> RECIBIDO
                "EN_PROCESO" -> EN_PROCESO
                "TERMINADO" -> TERMINADO
                else -> throw IllegalArgumentException("Estado no reconocido.")
            }
        }
    }
}