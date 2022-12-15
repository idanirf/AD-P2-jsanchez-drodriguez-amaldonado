/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package dto

import kotlinx.serialization.Serializable
import models.TareaEncordado
import models.TareaPersonalizacion

@Serializable
data class PersonalizacionDTO(
    val id: Int,
    val uuid: String,
    val precio: Double,
    val peso: Double,
    val balance: Double,
    val rigidez: Double,
    val pedido: String,
)

fun TareaPersonalizacion.toDTO(): PersonalizacionDTO {
    return PersonalizacionDTO(
        id = id,
        uuid = uuid.toString(),
        precio = precio,
        peso = peso,
        balance = balance,
        rigidez = rigidez,
        pedido = pedido.toString(),

        )
}