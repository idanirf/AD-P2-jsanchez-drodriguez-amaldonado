package entitities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

object TablaPedido : IntIdTable("PEDIDO") {
    val uuid = uuid("uuid").uniqueIndex()
    val estado = varchar("estado", 50)
    val fechaEntrada = date("fechaEntrada")
    val fechaSalidaProgramada = date("fechaProgramada")
    val fechaEntrega = date("fechaEntrega")
    val precio = double("precio")
    val usuario = reference("usuarioId", TablaUsuario)




}

/**
 * Pedidos d a o
 *
 * @constructor
 *
 * @param id
 */
class PedidosDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PedidosDAO>(TablaPedido)

    var uuid by TablaPedido.uuid
    var estado by TablaPedido.estado
    var fechaEntrada by TablaPedido.fechaEntrada
    var fechaSalidaProgramada by TablaPedido.fechaSalidaProgramada
    var fechaEntrega by TablaPedido.fechaEntrega
    var precio by TablaPedido.precio
    var usuario by UsuariosDAO referencedOn TablaPedido.usuario
}