package repositories.turno

import db.HibernateManager
import db.HibernateManager.manager
import models.Turno
import mu.KotlinLogging
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger{}
class TurnoRepositoryImplement : ITurnoRepository {
    override fun findAll(): List<Turno> {
        logger.debug { "findAll()" }
        var turno = mutableListOf<Turno>()
        HibernateManager.query {
            val query: TypedQuery<Turno> = manager.createNamedQuery("Turnos.findAll", Turno::class.java)
            turno = query.resultList
        }
        return turno
    }

    override fun findById(id: Int): Turno? {
        logger.debug { "findById($id)" }
        var turno: Turno? = null
        HibernateManager.query {
            turno = manager.find(Turno::class.java, id)
        }
        return turno
    }

    override fun save(entity: Turno): Turno {
        logger.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Turno): Boolean {
        var res = false
        HibernateManager.transaction {
            val turno = manager.find(Turno::class.java, entity.id)
            turno?.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }
}