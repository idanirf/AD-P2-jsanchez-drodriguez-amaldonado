package repositories.maquina

import db.HibernateManager
import db.HibernateManager.manager
import db.logger
import models.MaquinaPersonalizar
import javax.persistence.TypedQuery

class MaquinaPersonalizarRepositoryImplement : IMaquinaPersonalizarRepository {
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

    override fun findById(id: Int): MaquinaPersonalizar? {
        logger.debug { "findById($id)" }
        var maquina: MaquinaPersonalizar? = null
        HibernateManager.query {
            maquina = manager.find(MaquinaPersonalizar::class.java, id)
        }
        return maquina
    }

    override fun save(entity: MaquinaPersonalizar): MaquinaPersonalizar {
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

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
