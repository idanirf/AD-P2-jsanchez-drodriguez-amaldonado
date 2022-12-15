package repositories.maquina

import db.HibernateManager
import db.HibernateManager.manager
import models.MaquinaEncordar
import mu.KotlinLogging
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}

class MaquinaEncordarRepositoryImplement : IMaquinaEncordarRepository {
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

    override fun findById(id: Int): MaquinaEncordar? {
        logger.debug { "findById($id)" }
        var maquina: MaquinaEncordar? = null
        HibernateManager.query {
            maquina = manager.find(MaquinaEncordar::class.java, id)
        }
        return maquina
    }

    override fun save(entity: MaquinaEncordar): MaquinaEncordar {
        logger.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(id: Int): Boolean {
        var res = false
        HibernateManager.transaction {
            val maquina = findById(id)
            if (maquina != null) {
                manager.remove(maquina)
                res = true
            }
        }
        return res
    }
}