package repositories.tareas

import db.HibernateManager
import db.HibernateManager.manager
import models.TareaEncordado
import mu.KotlinLogging
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}

class TareaEncordadoRepositoryImplement : ITareaEncordadoRepository {
    /**
     * Find all
     *
     * @return Devuelve todas las tareas encordadas.
     */
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

    /**
     * Find by id
     *
     * @param id
     * @return Devuelve la tarea encordada por su id.
     */
    override fun findById(id: Int): TareaEncordado? {
        logger.debug { "findById($id)" }
        var tarea: TareaEncordado? = null
        HibernateManager.query {
            tarea = manager.find(TareaEncordado::class.java, id)
        }
        return tarea
    }

    /**
     * Save
     *
     * @param entity
     * @return Devuelve la tarea encordada insertada.
     */
    override fun save(entity: TareaEncordado): TareaEncordado {
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
     * @return Devuelve true si se ha borrado la tarea encordada o false en caso negativo.
     */
    override fun delete(entity: TareaEncordado): Boolean {
        var res = false
        HibernateManager.transaction {
            val tarea = manager.find(TareaEncordado::class.java, entity.id)
            tarea?.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }
}