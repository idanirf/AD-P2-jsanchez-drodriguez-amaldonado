package models.enums

/**
 * Numero nudos: Es una clase Enum
 *
 * @constructor
 *
 * @param numeroNudos
 */
enum class NumeroNudos(numeroNudos: String) {
    DOS("DOS"),
    CUATRO("CUATRO");

    companion object {
        fun from(numeroNudos: String): NumeroNudos {
            return when (numeroNudos.uppercase()) {
                "DOS" -> DOS
                "CUATRO" -> CUATRO
                else -> throw IllegalArgumentException("Nudos no válidos")
            }
        }
    }
}