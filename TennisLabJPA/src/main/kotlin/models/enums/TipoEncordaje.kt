package models.enums


enum class TipoEncordaje(valor: String) {
    AUTOMATICA("AUTOMATICA"),
    MANUAL("MANUAL");

    companion object {
        fun from(tipoEncordaje: String): TipoEncordaje {
            return when (tipoEncordaje.uppercase()) {
                "MANUAL" -> MANUAL
                "AUTOMATICA" -> AUTOMATICA
                else -> throw IllegalArgumentException("TipoEncordaje no reconocido")
            }
        }
    }
}