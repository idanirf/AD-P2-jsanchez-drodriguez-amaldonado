package dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import models.Pedido
import models.Producto
import models.enums.TipoProducto
import java.util.*

/**
 * Producto d t o
 *
 * @property id
 * @property uuid
 * @property marca
 * @property modelo
 * @property precio
 * @property stock
 * @property tipoProducto
 * @property pedido
 * @constructor Create empty Producto d t o
 */
@Serializable
@SerialName("Producto")
data class ProductoDTO(
    val id: Int,
    val uuid: String,
    val marca: String,
    val modelo: String,
    val precio: Double,
    val stock: Int,
    val tipoProducto: String,
    val pedido: String
)

/**
 * To d t o
 *
 * @return
 */
fun Producto.toDTO(): ProductoDTO {
    return ProductoDTO(
        id = id,
        uuid = uuid.toString(),
        marca= marca,
        modelo= modelo,
        precio=precio,
        stock=stock,
        tipoProducto=tipoProducto.toString(),
        pedido=pedido.toString()

    )
}
