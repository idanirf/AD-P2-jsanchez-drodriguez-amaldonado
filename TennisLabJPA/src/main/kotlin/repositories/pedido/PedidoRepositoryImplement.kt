package repositories.pedido

import db.HibernateManager
import db.HibernateManager.manager
import models.Pedido
import mu.KotlinLogging
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}

class PedidoRepositoryImplement : IPedidoRepository {
    /**
     * Find all
     *
     * @return Devuelve todos los pedidos.
     */
    override fun findAll(): List<Pedido> {
        logger.debug { "findAll()" }
        var pedidos = mutableListOf<Pedido>()
        HibernateManager.query {
            val query: TypedQuery<Pedido> = manager.createNamedQuery("Pedido.findAll", Pedido::class.java)
            pedidos = query.resultList
        }
        return pedidos
    }

    /**
     * Find by id
     *
     * @param id
     * @return Devuelve el pedido por su id.
     */
    override fun findById(id: Int): Pedido? {
        logger.debug { "findById($id)" }
        var pedido: Pedido? = null
        HibernateManager.query {
            pedido = manager.find(Pedido::class.java, id)
        }
        return pedido
    }

    /**
     * Save
     *
     * @param entity
     * @return Devuelve el pedido insertado.
     */
    override fun save(entity: Pedido): Pedido {
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    /**
     * Delete
     *
     * @param entity
     * @return Devuelve true si se ha borrado el pedido o false en caso negativo.
     */
    override fun delete(entity: Pedido): Boolean {
        var res = false
        HibernateManager.transaction {
            val pedido = manager.find(Pedido::class.java, entity.id)
            pedido?.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }
}