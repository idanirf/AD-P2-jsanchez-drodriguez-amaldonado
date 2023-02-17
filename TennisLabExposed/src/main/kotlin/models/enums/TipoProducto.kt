package models.enums

/**
 * Tipo producto: Es una clase Enum
 *
 * @property tipoProducto
 * @constructor Create empty Tipo producto
 */
enum class TipoProducto(val tipoProducto: String) {
    RAQUETA("RAQUETA"),
    CORDAJE("CORDAJE"),
    COMPLEMENTO("COMPLEMENTO");
    companion object {
        fun from(producto: String): TipoProducto {
            return when (producto.uppercase()) {
                "RAQUETA" -> RAQUETA
                "CORDAJE" -> CORDAJE
                "COMPLEMENTO" -> COMPLEMENTO
                else -> throw IllegalArgumentException("Producto no válido.")
            }
        }
    }
}