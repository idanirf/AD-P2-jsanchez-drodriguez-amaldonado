package entitities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object TablaTurno : IntIdTable("TURNO") {
    val uuid = uuid("uuid").uniqueIndex()
    val fechaInicio = datetime("fechaInicio")
    val fechaFinal = datetime("fechaFinal")
    val usuario = reference("usuario_uuid", TablaUsuario)
}

/**
 * Turno d a o
 *
 * @constructor
 *
 * @param id
 */
class TurnoDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TurnoDAO>(TablaTurno)

    var uuid by TablaTurno.uuid
    var fechaInicio by TablaTurno.fechaInicio
    var fechaFinal by TablaTurno.fechaFinal
    var usuario by UsuariosDAO referencedOn TablaTurno.usuario
}