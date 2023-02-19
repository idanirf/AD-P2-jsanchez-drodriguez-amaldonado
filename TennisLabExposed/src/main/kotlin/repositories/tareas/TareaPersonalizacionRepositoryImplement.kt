package repositories.tareas

import entitities.PedidosDAO
import entitities.TareasPersonalizacionDAO
import exception.TareaPersonalizacionException
import mapper.fromTareasPersonalizacionDAOToTareasPersonalizacion
import models.TareaPersonalizacion
import mu.KotlinLogging
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Tarea personalizacion repository implement
 *
 * @property tareasDAO
 * @property pedidosDAO
 * @constructor Create empty Tarea personalizacion repository implement
 */
class TareaPersonalizacionRepositoryImplement(
    private val tareasDAO: IntEntityClass<TareasPersonalizacionDAO>,
    private val pedidosDAO: IntEntityClass<PedidosDAO>
) : ITareaPersonalizacionRepository {

    private val logger = KotlinLogging.logger { }

    /**
     * Método encargado de ejecutar transacción, con una consulta de los DAO en su interior, la cual se encarga de
     * devolver todos los objetos de un tipo en concreto que estén almacenados en la base de datos
     *
     * @return List<TareaPersonalizacion>, la lista de objetos encontrada en su respectiva base de datos convertido de DAO a modelo.
     */
    override fun findAll(): List<TareaPersonalizacion> = transaction {
        logger.debug { "findAll() - buscando todos" }
        tareasDAO.all().map { it.fromTareasPersonalizacionDAOToTareasPersonalizacion() }
    }

    /**
     * Método encargado de ejecutar transacción, con una consulta del DAO en su interior, la cual se encarga de
     * devolver el objeto de un tipo en concreto que tiene un identificador específico.
     *
     * @param id identificador de tipo Integer del objeto a consultar.
     *
     * @throws TareaPersonalizacionException si la maquina no se encuentra.
     *
     * @return TareaPersonalizacion, el objeto que tiene el identificador introducido convertido de DAO a modelo.
     */
    override fun findById(id: Int): TareaPersonalizacion {
        logger.debug { "findById($id) - buscando $id" }
        return tareasDAO.findById(id)
            ?.fromTareasPersonalizacionDAOToTareasPersonalizacion()
            ?: throw TareaPersonalizacionException("Tarea no encontrada con id $id")
    }

    /**
     * Método encargado de ejecutar transacción, con una inserción del DAO a la base de datos en su interior.
     * Si existe el objeto a insertar, lo actualizará. En caso contrario simplemente hará la inserción de un nuevo objeto.
     *
     * @param entity objeto a insetar o actualizar en la base de datos.
     *
     * @return TareaPersonalizacion, el objeto que ha sido insertado o actualizado convertido de DAO a modelo.
     */
    override fun save(entity: TareaPersonalizacion): TareaPersonalizacion = transaction {
        val existe = tareasDAO.findById(entity.id)

        existe?.let {
            update(entity, existe)
        } ?: run {
            insert(entity)
        }
    }

    /**
     * Método encargado de realizar la actualización del DAO en caso de que la entidad a introducir exista en la base de datos.
     *
     * @param entity objeto a actualizar.
     * @param existe objeto DAO si existe, nulo si no.
     *
     * @return TareaPersonalizacion, el objeto que ha sido actualizado convertido de DAO a modelo.
     */
    private fun update(entity: TareaPersonalizacion, existe: TareasPersonalizacionDAO): TareaPersonalizacion {
        logger.debug { "save($entity) - actualizando" }
        return existe.apply {
            uuid = entity.uuid
            rigidez = entity.rigidez
            peso = entity.peso
            balance = entity.balance
            precio = entity.precio
            pedido = pedidosDAO.findById(entity.pedido.id)!!


        }.fromTareasPersonalizacionDAOToTareasPersonalizacion()
    }

    /**
     * Método encargado de realizar la inserción del DAO en caso de que la entidad a introducir no exista en la base de datos.
     *
     * @param entity objeto a insetar.
     *
     * @return TareaPersonalizacion, el objeto que ha sido insertado convertido de DAO a modelo.
     */
    private fun insert(entity: TareaPersonalizacion): TareaPersonalizacion {
        logger.debug { "save($entity) - creando" }
        return tareasDAO.new(entity.id) {
            uuid = entity.uuid
            rigidez = entity.rigidez
            peso = entity.peso
            balance = entity.balance
            precio = entity.precio
            pedido = pedidosDAO.findById(entity.pedido.id)!!


        }.fromTareasPersonalizacionDAOToTareasPersonalizacion()
    }


    /**
     * Método encargado de ejecutar transacción, con un borrado del DAO de la base de datos en su interior.
     *
     * @param entity objeto a borrar en la base de datos.
     *
     * @return Boolean, true en caso de que se haya podido borrar el objeto, false si no se ha podido encontrar y por lo tanto borrar convertido de DAO a modelo.
     */
    override fun delete(entity: TareaPersonalizacion): Boolean = transaction {
        val existe = tareasDAO.findById(entity.id) ?: return@transaction false
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }
}