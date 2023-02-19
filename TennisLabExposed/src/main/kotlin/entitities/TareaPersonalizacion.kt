package entitities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object TablaTareaPersonalizacion : IntIdTable("TAREAPERSONALIZACION") {
    val uuid = uuid("uuid").uniqueIndex()
    val rigidez = double("rigidez")
    val peso = double("peso")
    val balance = double("balance")
    val precio = double("precio")
    val pedido = reference("pedido", TablaPedido)



}

/**
 * Tareas personalizacion d a o
 *
 * @constructor
 *
 * @param id
 */
class TareasPersonalizacionDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TareasPersonalizacionDAO>(TablaTareaPersonalizacion)

    var uuid by TablaTareaPersonalizacion.uuid
    var rigidez by TablaTareaPersonalizacion.rigidez
    var peso by TablaTareaPersonalizacion.peso
    var balance by TablaTareaPersonalizacion.balance
    var precio by TablaTareaPersonalizacion.precio
    var pedido by PedidosDAO referencedOn TablaTareaPersonalizacion.pedido



}