package entitities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object TablaTareaEncordado : IntIdTable("TAREAENCORDADO") {
    val uuid = uuid("uuid").uniqueIndex()
    val precio = double("precio")
    val tensionVertical = double("tension_vertical")
    val cordajeVertical = varchar("cordaje_vertical", 255)
    val tensionHorizontal = double("tension_horizontal")
    val cordajeHorizontal = varchar("cordaje_horizontal", 255)
    val nudos = varchar("nudos", 50)
    val pedido = reference("pedido", TablaPedido)
}

/**
 * Tareas encordado d a o
 *
 * @constructor
 *
 * @param id
 */
class TareasEncordadoDAO(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<TareasEncordadoDAO>(TablaTareaEncordado)

    var uuid by TablaTareaEncordado.uuid
    var precio by TablaTareaEncordado.precio
    var tensionVertical by TablaTareaEncordado.tensionVertical
    var cordajeVertical by TablaTareaEncordado.cordajeVertical
    var tensionHorizontal by TablaTareaEncordado.tensionHorizontal
    var cordajeHorizontal by TablaTareaEncordado.cordajeHorizontal
    var nudos by TablaTareaEncordado.nudos
    var pedido by PedidosDAO referencedOn TablaTareaEncordado.pedido
}