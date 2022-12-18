package repositories.pedido

import db.HibernateManager
import db.HibernateManager.manager
import models.Pedido
import mu.KotlinLogging
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}
class PedidoRepositoryImplement : iPedidoRepository {
    override fun findAll(): List<Pedido> {
        logger.debug { "findAll()" }
        var pedidos = mutableListOf<Pedido>()
        HibernateManager.query {
            val query: TypedQuery<Pedido> = manager.createNamedQuery("Pedido.findAll", Pedido::class.java)
            pedidos = query.resultList
        }
        return pedidos
    }

    override fun findById(id: Int): Pedido? {
        logger.debug { "findById($id)" }
        var pedido: Pedido? = null
        HibernateManager.query {
            pedido = manager.find(Pedido::class.java, id)
        }
        return pedido
    }

    override fun save(entity: Pedido): Pedido {
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(id: Int): Boolean {
        var res = false
        HibernateManager.transaction {
            val pedido = findById(id)
            if (pedido != null){
                manager.remove(pedido)
                res = true
            }
        }
        return res
    }
}