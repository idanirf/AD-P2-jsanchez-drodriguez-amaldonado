package repositories.maquina

import entitities.MaquinaPersonalizarDAO
import entitities.TurnoDAO
import entitities.UsuariosDAO
import exception.MaquinaPersonalizacionException
import mapper.fromMaquinaPersonalizarDAOToMaquinaPersonalizar
import models.MaquinaPersonalizar
import mu.KotlinLogging
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.transactions.transaction
import repositories.turno.TurnoRepositoryImplement

/**
 * Maquina personalizar repository implement
 *
 * @property maquinasPersonalizarDAO
 * @property turnosDAO
 * @constructor Create empty Maquina personalizar repository implement
 */
class MaquinaPersonalizarRepositoryImplement(
    private val maquinasPersonalizarDAO: IntEntityClass<MaquinaPersonalizarDAO>,
    private val turnosDAO: IntEntityClass<TurnoDAO>

) : IMaquinaPersonalizarRepository {
    private val turnosRepository = TurnoRepositoryImplement(turnosDAO, UsuariosDAO)
    private val logger = KotlinLogging.logger {}


    override fun findAll(): List<MaquinaPersonalizar> = transaction {
        logger.debug { "findAll() - buscando todos" }
        maquinasPersonalizarDAO.all().map { it.fromMaquinaPersonalizarDAOToMaquinaPersonalizar() }
    }

    override fun findById(id: Int): MaquinaPersonalizar = transaction {
        logger.debug { "findById($id) - buscando $id" }
        maquinasPersonalizarDAO.findById(id)
            ?.fromMaquinaPersonalizarDAOToMaquinaPersonalizar()
            ?: throw MaquinaPersonalizacionException("Máquina de personalizar no encontrada con id: $id")
    }

    override fun save(entity: MaquinaPersonalizar): MaquinaPersonalizar = transaction {
        val existe = maquinasPersonalizarDAO.findById(entity.id)

        val encordadorParaAsignar = entity.turno.usuario
        val encordadoresAsignados = turnosRepository.findAll().filter { it.id == entity.turno.id }.map { it.usuario }
        require(!encordadoresAsignados.contains(encordadorParaAsignar))
        { "Esta máquina no ha podido añadirse porque el encordador que quiere asignarle ya esta utilizando otra máquina en ese turno." }

        existe?.let {
            update(entity, existe)
        } ?: run {
            insert(entity)
        }
    }

    override fun delete(entity: MaquinaPersonalizar): Boolean = transaction {
        val existe = maquinasPersonalizarDAO.findById(entity.id) ?: return@transaction false
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }

    private fun insert(entity: MaquinaPersonalizar): MaquinaPersonalizar {
        logger.debug { "save($entity) - creando" }
        return maquinasPersonalizarDAO.new(entity.id) {
            uuid = entity.uuid
            marca = entity.marca
            modelo = entity.modelo
            fechaAdquisicion = entity.fechaAdquisicion
            numeroSerie = entity.numeroSerie
            swingweight = entity.swingweight
            balance = entity.balance
            rigidez = entity.rigidez
            turno = turnosDAO.findById(entity.turno.id)!!

        }.fromMaquinaPersonalizarDAOToMaquinaPersonalizar()
    }


    private fun update(entity: MaquinaPersonalizar, existe: MaquinaPersonalizarDAO): MaquinaPersonalizar {
        logger.debug { "save($entity) - actualizando" }
        return existe.apply {
            uuid = entity.uuid
            marca = entity.marca
            modelo = entity.modelo
            fechaAdquisicion = entity.fechaAdquisicion
            numeroSerie = entity.numeroSerie
            swingweight = entity.swingweight
            balance = entity.balance
            rigidez = entity.rigidez
            turno = turnosDAO.findById(entity.turno.id)!!

        }.fromMaquinaPersonalizarDAOToMaquinaPersonalizar()
    }
}