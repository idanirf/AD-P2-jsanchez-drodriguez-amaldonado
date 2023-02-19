package models.enums


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