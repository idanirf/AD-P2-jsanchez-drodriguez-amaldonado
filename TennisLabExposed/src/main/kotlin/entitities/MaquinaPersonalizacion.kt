package entitities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

object TablaMaquinaPersonalizar : IntIdTable("MAQUINAPERSONALIZAR") {
    val uuid = uuid("uuid").uniqueIndex()
    val marca = varchar("marca", 100)
    val modelo = varchar("modelo", 100)
    val fechaAdquisicion = date("fechaAdquisicion")
    val numeroSerie = integer("numeroSerie")
    val swingweight = bool("swingweight")
    val balance = double("balance")
    val rigidez = double("rigidez")
    val turno = reference("turno", TablaTurno)
}

/**
 * Maquina personalizar d a o
 *
 * @constructor
 *
 * @param id
 */
class MaquinaPersonalizarDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MaquinaPersonalizarDAO>(TablaMaquinaPersonalizar)

    var uuid by TablaMaquinaPersonalizar.uuid
    var marca by TablaMaquinaPersonalizar.marca
    var modelo by TablaMaquinaPersonalizar.modelo
    var fechaAdquisicion by TablaMaquinaPersonalizar.fechaAdquisicion
    var numeroSerie by TablaMaquinaPersonalizar.numeroSerie
    var swingweight by TablaMaquinaPersonalizar.swingweight
    var balance by TablaMaquinaPersonalizar.balance
    var rigidez by TablaMaquinaPersonalizar.rigidez
    var turno by TurnoDAO referencedOn TablaMaquinaPersonalizar.turno
}