package repositories.usuario

import entitities.UsuariosDAO
import exception.UsuarioException
import mapper.fromUsuarioDAOToUsuario
import models.Usuario
import mu.KotlinLogging
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.transactions.transaction
import services.encriptar

/**
 * Usuario repository implement
 *
 * @property usuariosDAO
 * @constructor Create empty Usuario repository implement
 */
class UsuarioRepositoryImplement (
    private val usuariosDAO: IntEntityClass<UsuariosDAO>
) : IUsuarioRepository {

    private val logger = KotlinLogging.logger {}

    override fun findAll(): List<Usuario> = transaction {
        logger.debug { "findAll() - buscando todos " }
        usuariosDAO.all().map { it.fromUsuarioDAOToUsuario() }
    }


    override fun findById(id: Int): Usuario = transaction {
        logger.debug { "findById($id) - buscando $id" }
        usuariosDAO.findById(id)
            ?.fromUsuarioDAOToUsuario() ?: throw UsuarioException("Usuario no encontrado con id: $id")
    }



    override fun save(entity: Usuario): Usuario = transaction {
        val existe = usuariosDAO.findById(entity.id)

        existe?.let {
            update(entity, existe)
        } ?: run {
            insert(entity)
        }
    }


    override fun delete(entity: Usuario): Boolean = transaction {
        val existe = usuariosDAO.findById(entity.id) ?: return@transaction false
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }


    private fun insert(entity: Usuario): Usuario {
        logger.debug { "save($entity) - creando" }
        return usuariosDAO.new(entity.id) {
            uuid = entity.uuid
            nombre = entity.nombre
            apellido = entity.apellido
            correo = entity.correo
            password = encriptar(entity.password)
            tipoUsuario = entity.tipoUsuario
        }.fromUsuarioDAOToUsuario()
    }


    private fun update(entity: Usuario, existe: UsuariosDAO): Usuario {
        logger.debug { "save($entity) - actualizando" }
        return existe.apply {
            uuid = entity.uuid
            nombre = entity.nombre
            apellido = entity.apellido
            correo = entity.correo
            password = entity.password
            tipoUsuario = entity.tipoUsuario
        }.fromUsuarioDAOToUsuario()
    }
}