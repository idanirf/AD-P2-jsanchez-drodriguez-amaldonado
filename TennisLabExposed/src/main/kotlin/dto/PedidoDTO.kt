package dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import models.Pedido
import java.util.*

/**
 * Pedido dto
 *
 * @property id
 * @property uuid
 * @property estado
 * @property fechaEntrada
 * @property fechaSalidaProgramada
 * @property fechaEntrega
 * @property precio
 * @property usuario
 */
@Serializable
@SerialName("Pedido")
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

/**
 * To d t o
 *
 * @return Objeto en dto.
 */
fun Pedido.toDTO(): PedidoDTO {
    return PedidoDTO(
        id = id,
        uuid=uuid.toString(),
        estado=estado.toString(),
        fechaEntrada=fechaEntrada.toString(),
        fechaSalidaProgramada=fechaSalidaProgramada.toString(),
        fechaEntrega=fechaEntrega.toString(),
        precio=precio,
        usuario = usuario.toString()
    )
}