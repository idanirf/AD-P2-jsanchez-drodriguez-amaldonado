package controller

import entitities.PedidosDAO
import entitities.UsuariosDAO
import models.Pedido
import repositories.pedido.PedidoRepositoryImplement

/**
 * Pedidos controller
 *
 * @property pedidoRepositoryImplement
 * @constructor Create empty Pedidos controller
 */
class PedidosController (
    private val pedidoRepositoryImplement: PedidoRepositoryImplement = PedidoRepositoryImplement(
        PedidosDAO,
        UsuariosDAO
    )
) {
    /**
     * Find all: Encuentra todos los pedidos.
     *
     * @return
     */
    fun findAll(): List<Pedido> {
        return pedidoRepositoryImplement.findAll()
    }

    /**
     * Find by i d
     *
     * @param id
     * @return
     */
    fun findByID(id: Int): Pedido {
        return pedidoRepositoryImplement.findById(id)
    }

    /**
     * Save
     *
     * @param entity
     * @return
     */
    fun save(entity: Pedido): Pedido {
        return pedidoRepositoryImplement.save(entity)
    }

    /**
     * Delete
     *
     * @param entity
     * @return
     */
    fun delete(entity: Pedido): Boolean {
        return pedidoRepositoryImplement.delete(entity)
    }
}