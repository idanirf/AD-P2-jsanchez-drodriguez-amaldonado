package mapper

import entitities.PedidosDAO
import models.Pedido
import models.enums.TipoEstado

/**
 * From pedidos d a o to pedidos
 *
 * @return El objeto ya en modelo.
 */
fun PedidosDAO.fromPedidosDAOToPedidos(): Pedido {
    return Pedido(
        id = id.value,
        uuid = uuid,
        estado = TipoEstado.from(estado),
        fechaEntrada = fechaEntrada,
        fechaSalidaProgramada = fechaSalidaProgramada,
        fechaEntrega = fechaEntrega,
        precio = precio,
        usuario = usuario.fromUsuarioDAOToUsuario(),




    )
}