package repositories.pedido

import models.Pedido
import repositories.ICRUDRepository

/**
 * I pedido repository
 *
 * @constructor Create empty I pedido repository
 */
interface IPedidoRepository: ICRUDRepository<Pedido, Int> {
}