/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package dto

import kotlinx.serialization.Serializable
import models.Pedido

@Serializable
data class PedidoDTO(
    val id: Int,
    val uuid: String,
    val estado: String,
    val fechaEntrada: String,
    val fechaSalidaProgramada: String,
    val fechaEntrega: String,
    val precio: Double,
    val usuario: String,

    )

fun Pedido.toDTO(): PedidoDTO {
    return PedidoDTO(
        id = id,
        uuid = uuid.toString(),
        estado = estado.toString(),
        usuario = usuario.toString(),
        fechaEntrada = fechaEntrada.toString(),
        fechaSalidaProgramada = fechaSalidaProgramada.toString(),
        fechaEntrega = fechaEntrega.toString(),
        precio = precio
    )
}