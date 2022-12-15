package repositories.tareas

import db.HibernateManager
import db.HibernateManager.manager
import models.TareaEncordado
import mu.KotlinLogging
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}

class TareaEncordadoRepository : ITareaEncordadoRepository {
    override fun findAll(): List<TareaEncordado> {
        logger.debug { "findAll()" }
        var tareas = mutableListOf<TareaEncordado>()
        HibernateManager.query {
            val query: TypedQuery<TareaEncordado> =
                manager.createNamedQuery("TareaEncordado.findAll", TareaEncordado::class.java)
            tareas = query.resultList
        }
        return tareas
    }

    override fun findById(id: Int): TareaEncordado? {
        logger.debug { "findById($id)" }
        var tarea: TareaEncordado? = null
        HibernateManager.query {
            tarea = manager.find(TareaEncordado::class.java, id)
        }
        return tarea
    }

    override fun save(entity: TareaEncordado): TareaEncordado {
        logger.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(id: Int): Boolean {
        var res = false
        HibernateManager.transaction {
            val tarea = findById(id)
            if (tarea != null) {
                manager.remove(tarea)
                res = true
            }
        }
        return res
    }
}