package models

import models.enums.TipoProducto
import java.util.*

/**
 * Producto
 *
 * @property id
 * @property uuid
 * @property marca
 * @property modelo
 * @property precio
 * @property stock
 * @property tipoProducto
 * @property pedido
 * @constructor Create empty Producto
 */
data class Producto(
    val id : Int,
    val uuid: UUID,
    val marca: String,
    val modelo: String,
    val precio: Double,
    val stock: Int,
    val tipoProducto: TipoProducto,
    val pedido: Pedido
)