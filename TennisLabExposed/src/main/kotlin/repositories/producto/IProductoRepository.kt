package repositories.producto

import models.Producto
import repositories.ICRUDRepository

/**
 * I producto repository
 *
 * @constructor Create empty I producto repository
 */
interface IProductoRepository: ICRUDRepository<Producto, Int> {
}