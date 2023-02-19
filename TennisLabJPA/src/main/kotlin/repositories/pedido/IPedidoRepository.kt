package repositories.pedido

import models.Pedido
import repositories.ICRUDRepository

interface IPedidoRepository : ICRUDRepository<Pedido, Int>