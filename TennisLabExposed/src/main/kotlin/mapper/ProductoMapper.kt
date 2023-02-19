package mapper

import entitities.ProductosDAO
import models.Producto

/**
 * From productos d a o to producto
 *
 * @return El objeto ya en modelo.
 */
fun ProductosDAO.fromProductosDAOToProducto(): Producto {
    return Producto(
        id = id.value,
        uuid = uuid,
        marca = marca,
        modelo = modelo,
        precio = precio,
        stock = stock,
        tipoProducto = tipoProducto,
        pedido = pedido.fromPedidosDAOToPedidos()
    )
}