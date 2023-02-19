package repositories.maquina

import db.HibernateManager
import db.HibernateManager.manager
import db.logger
import models.MaquinaPersonalizar
import javax.persistence.TypedQuery

class MaquinaPersonalizarRepositoryImplement : IMaquinaPersonalizarRepository {
    /**
     * Find all
     *
     * @return Devuelve todas las Máquinas Personalizar.
     */
    override fun findAll(): List<MaquinaPersonalizar> {
        logger.debug { "findAll()" }
        var maquinas = mutableListOf<MaquinaPersonalizar>()
        HibernateManager.query {
            val query: TypedQuery<MaquinaPersonalizar> =
                manager.createNamedQuery("MaquinaPersonalizar.findAll", MaquinaPersonalizar::class.java)
            maquinas = query.resultList
        }
        return maquinas
    }

    /**
     * Find by id
     *
     * @param id
     * @return Devuelve la máquina personalizar por su id.
     */
    override fun findById(id: Int): MaquinaPersonalizar? {
        logger.debug { "findById($id)" }
        var maquina: MaquinaPersonalizar? = null
        HibernateManager.query {
            maquina = manager.find(MaquinaPersonalizar::class.java, id)
        }
        return maquina
    }

    /**
     * Save
     *
     * @param entity
     * @return  Devuelve la máquina personalizar insertada.
     */
    override fun save(entity: MaquinaPersonalizar): MaquinaPersonalizar {
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
    override fun delete(entity: MaquinaPersonalizar): Boolean {
        var res = false
        HibernateManager.transaction {
            val maquina = manager.find(MaquinaPersonalizar::class.java, entity.id)
            maquina?.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }

}
