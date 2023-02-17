package repositories.turno

import entitities.TurnoDAO
import entitities.UsuariosDAO
import exception.TurnoException
import exception.UsuarioException
import mapper.fromTurnoDAOToTurno
import models.Turno
import mu.KotlinLogging
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Turno repository implement
 *
 * @property turnosDAO
 * @property usuariosDAO
 * @constructor Create empty Turno repository implement
 */
class TurnoRepositoryImplement(
    private val turnosDAO: IntEntityClass<TurnoDAO>,
    private val usuariosDAO: IntEntityClass<UsuariosDAO>
) : ITurnoRepository {
    private val logger = KotlinLogging.logger {}



    override fun findAll(): List<Turno> = transaction {
        logger.debug { "findAll() - buscando todos " }
        turnosDAO.all().map { it.fromTurnoDAOToTurno() }
    }


    override fun findById(id: Int): Turno = transaction {
        logger.debug { "findById($id) - buscando $id" }
        turnosDAO.findById(id)
            ?.fromTurnoDAOToTurno() ?: throw TurnoException("Turno no encontrado con id: $id")
    }


    override fun save(entity: Turno): Turno = transaction {
        val existe = turnosDAO.findById(entity.id)

        existe?.let {
            update(entity, existe)
        } ?: run {
            insert(entity)
        }
    }


    override fun delete(entity: Turno): Boolean = transaction {
        val existe = turnosDAO.findById(entity.id) ?: return@transaction false
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }


    private fun insert(entity: Turno): Turno {

        logger.debug { "save($entity) - creando" }
        return turnosDAO.new(entity.id) {
            uuid = entity.uuid
            fechaInicio = entity.fechaInicio
            fechaFinal = entity.fechaFinal
            usuario = usuariosDAO.findById(entity.usuario.id)
                ?: throw UsuarioException("El encordador con id: $id no existe.")
        }.fromTurnoDAOToTurno()
    }

    private fun update(entity: Turno, existe: TurnoDAO): Turno {
        logger.debug { "save($entity) - actualizando" }
        return existe.apply {
            uuid = entity.uuid
            fechaInicio = entity.fechaInicio
            fechaFinal = entity.fechaFinal
            usuario = usuariosDAO.findById(entity.usuario.id)
                ?: throw UsuarioException("El encordador con id: $id no existe.")
        }.fromTurnoDAOToTurno()
    }
}