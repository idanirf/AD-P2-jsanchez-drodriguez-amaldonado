package repositories.tareas

import db.HibernateManager
import db.HibernateManager.manager
import models.TareaPersonalizacion
import mu.KotlinLogging
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}
class TareaPersonalizacionRepositoryImplement : ITareaPersonalizacionRepository {
    override fun findAll(): List<TareaPersonalizacion> {
        logger.debug { "findAll()" }
        var tareas = mutableListOf<TareaPersonalizacion>()
        HibernateManager.query {
            val query: TypedQuery<TareaPersonalizacion> = HibernateManager.manager.createNamedQuery("TareaPersonalizacion.findAll", TareaPersonalizacion::class.java)
            tareas = query.resultList
        }
        return tareas
    }

    override fun findById(id: Int): TareaPersonalizacion? {
        logger.debug { "findById($id)" }
        var tarea: TareaPersonalizacion? = null
        HibernateManager.query {
            tarea = HibernateManager.manager.find(TareaPersonalizacion::class.java, id)
        }
        return tarea
    }

    override fun save(entity: TareaPersonalizacion): TareaPersonalizacion {
        logger.debug { "save($entity)" }
        HibernateManager.transaction {
            HibernateManager.manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: TareaPersonalizacion): Boolean {
        var res = false
        HibernateManager.transaction {
            val tarea = manager.find(TareaPersonalizacion::class.java, entity.id)
            tarea?.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }
}