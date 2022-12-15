/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package dto

import kotlinx.serialization.Serializable
import models.Producto


@Serializable
data class ProductoDTO(
    val id: Int,
    val uuid: String,
    val marca: String,
    val modelo: String,
    val precio: Double,
    val stock: Int,
    val pedido: String,
    val tipoProducto: String,

    )

fun Producto.toDTO(): ProductoDTO {
    return ProductoDTO(
        id = id,
        uuid = uuid.toString(),
        marca = marca.toString(),
        modelo = modelo.toString(),
        precio = precio,
        stock = stock,
        pedido = pedido.toString(),
        tipoProducto = tipoProducto.toString()
        )
}