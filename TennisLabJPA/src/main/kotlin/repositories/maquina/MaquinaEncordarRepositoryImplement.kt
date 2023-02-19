package repositories.maquina

import db.HibernateManager
import db.HibernateManager.manager
import models.MaquinaEncordar
import mu.KotlinLogging
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}

class MaquinaEncordarRepositoryImplement : IMaquinaEncordarRepository {
    /**
     * Find all
     *
     * @return Devuelve todas las Máquinas Enncordadas.
     */
    override fun findAll(): List<MaquinaEncordar> {
        logger.debug { "findAll()" }
        var maquinas = mutableListOf<MaquinaEncordar>()
        HibernateManager.query {
            val query: TypedQuery<MaquinaEncordar> =
                manager.createNamedQuery("MaquinaEncordar.findAll", MaquinaEncordar::class.java)
            maquinas = query.resultList
        }
        return maquinas
    }

    /**
     * Find by id
     *
     * @param id
     * @return Devuelve la máquina encordada por su id
     */
    override fun findById(id: Int): MaquinaEncordar? {
        logger.debug { "findById($id)" }
        var maquina: MaquinaEncordar? = null
        HibernateManager.query {
            maquina = manager.find(MaquinaEncordar::class.java, id)
        }
        return maquina
    }

    /**
     * Save
     *
     * @param entity
     * @return Devuelve la máquina encordada insertada.
     */
    override fun save(entity: MaquinaEncordar): MaquinaEncordar {
        logger.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    /**
     * Delete
     *
     * @param entity
     * @return Devuelve true si se ha borrado la máquina o false en caso negativo.
     */
    override fun delete(entity: MaquinaEncordar): Boolean {
        var res = false
        HibernateManager.transaction {
            val maquina = manager.find(MaquinaEncordar::class.java, entity.id)
            maquina?.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }
}