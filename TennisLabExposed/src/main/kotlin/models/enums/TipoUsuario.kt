package models.enums

/**
 * Tipo usuario: Es una clase Enum
 *
 * @constructor
 *
 * @param valor
 */
enum class TipoUsuario(valor: String){
    USUARIO("USUARIO"),
    ADMINISTRADOR("ADMINISTRADOR"),
    ENCORDADOR("ENCORDADOR");
    companion object {
        fun from(tipoUsuario: String): TipoUsuario {
            return when (tipoUsuario.uppercase()) {
                "USUARIO" -> USUARIO
                "ADMINISTRADOR" -> ADMINISTRADOR
                "ENCORDADOR" -> ENCORDADOR
                else -> throw IllegalArgumentException("Usuario no reconocido")
            }
        }
    }
}