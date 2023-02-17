package entitities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

object TablaMaquinaEncordar : IntIdTable("MAQUINAENCORDAR") {
    val uuid = uuid("uuid").uniqueIndex()
    val marca = varchar("marca", 100 )
    val modelo = varchar("modelo", 100)
    val fechaAdquisicion = date("fechaAdquisicion")
    val numeroSerie = integer("numeroSerie")
    val tipo = varchar("tipo" , 10)
    val tensionMaxima = double("tensionMaxima")
    val tensionMinima = double("tensionMinima")
    val turno = reference("turno", TablaTurno)
}

/**
 * Maquinas encordar d a o
 *
 * @constructor
 *
 * @param id
 */
class MaquinasEncordarDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MaquinasEncordarDAO>(TablaMaquinaEncordar)
    var uuid by TablaMaquinaEncordar.uuid
    var marca by TablaMaquinaEncordar.marca
    var modelo by TablaMaquinaEncordar.modelo
    var fechaAdquisicion by TablaMaquinaEncordar.fechaAdquisicion
    var numeroSerie by TablaMaquinaEncordar.numeroSerie
    var tipo by TablaMaquinaEncordar.tipo
    var tensionMaxima by TablaMaquinaEncordar.tensionMaxima
    var tensionMinima by TablaMaquinaEncordar.tensionMinima
    var turno by TurnoDAO referencedOn TablaMaquinaEncordar.turno
}