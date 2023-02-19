package controller

import models.Pedido
import repositories.pedido.IPedidoRepository
import repositories.pedido.PedidoRepositoryImplement

class PedidoController(
    private val pedidoRepository: IPedidoRepository = PedidoRepositoryImplement()
) {
    /**
     * Find all
     *
     * @return Devuelve todos los pedidos.
     */
    fun findAll(): List<Pedido> {
        return pedidoRepository.findAll()
    }

    /**
     * Find by id
     *
     * @param id
     * @return Devuelve el pedido por su id.
     */
    fun findById(id: Int): Pedido? {
        return pedidoRepository.findById(id)
    }

    /**
     * Save
     *
     * @param pedido
     * @return Devuelve el pedido insertado.
     */
    fun save(pedido: Pedido): Pedido {
        return pedidoRepository.save(pedido)
    }

    /**
     * Delete
     *
     * @param pedido
     * @return True, si se ha borrado, False si no lo ha borrado.
     */
    fun delete(pedido: Pedido): Boolean {
        return pedidoRepository.delete(pedido)
    }


}