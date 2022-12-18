package repositories.pedido

import models.Pedido
import repositories.ICRUDRepository

interface iPedidoRepository : ICRUDRepository<Pedido, Int> {
}