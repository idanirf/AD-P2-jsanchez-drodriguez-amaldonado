package entitities

import models.enums.TipoProducto
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object TablaProducto : IntIdTable("PRODUCTO") {
    val uuid = uuid("uuid").uniqueIndex()
    val marca = varchar("marca", 255)
    val modelo = varchar("modelo", 255)
    val precio = double("precio")
    val stock = integer("stock")
    val tipoProducto = enumeration<TipoProducto>("tipo_producto")
    val pedido = reference("pedido_uuid", TablaPedido)

}

/**
 * Productos d a o
 *
 * @constructor
 *
 * @param id
 */
class ProductosDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProductosDAO>(TablaProducto)

    var uuid by TablaProducto.uuid
    var marca by TablaProducto.marca
    var modelo by TablaProducto.modelo
    var precio by TablaProducto.precio
    var stock by TablaProducto.stock
    var tipoProducto by TablaProducto.tipoProducto
    var pedido by PedidosDAO referencedOn TablaProducto.pedido
}