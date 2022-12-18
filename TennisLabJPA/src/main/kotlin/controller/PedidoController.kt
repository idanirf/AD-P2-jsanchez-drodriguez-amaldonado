package controller

import models.Pedido
import repositories.pedido.IPedidoRepository
import repositories.pedido.PedidoRepositoryImplement

class PedidoController(
    private val pedidoRepository: IPedidoRepository = PedidoRepositoryImplement()
) {
    fun findAll(): List<Pedido> {
        return pedidoRepository.findAll()
    }

    fun findById(id: Int): Pedido? {
        return pedidoRepository.findById(id)
    }

    fun save(pedido: Pedido): Pedido {
        return pedidoRepository.save(pedido)
    }

    fun delete(pedido: Pedido): Boolean {
        return pedidoRepository.delete(pedido)
    }


}