/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package dto

import kotlinx.serialization.Serializable
import models.Pedido
import models.TareaEncordado

@Serializable
data class EncordadoDTO(
    val id: Int,
    val uuid: String,
    val precio: Double,
    val pedido: String,
    val tensionVertical: Double,
    val cordajeVertical: String,
    val tensionHorizontal: Double,
    val cordajeHorizontal: String,
    val nudos: String
)


fun TareaEncordado.toDTO(): EncordadoDTO {
    return EncordadoDTO(
        id = id,
        uuid = uuid.toString(),
        precio = precio,
        tensionVertical = tensionVertical,
        cordajeVertical = cordajeVertical,
        tensionHorizontal = tensionHorizontal,
        cordajeHorizontal = cordajeHorizontal,
        nudos = nudos.toString(),
        pedido = pedido.toString(),

        )
}