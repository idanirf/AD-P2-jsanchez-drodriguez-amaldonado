/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package dto

import kotlinx.serialization.Serializable
import models.Turno

@Serializable
data class TurnoDTO(
    val id: Int,
    val uuid: String,
    val fechaInicio: String,
    val fechaFinal: String,
    val usuario: String
)

/**
 *
 * Sirve para pasar a ficheros de una forma más simple, cuando lo llamemos en el main para las consultas.
 */
fun Turno.toDTO(): TurnoDTO {
    return TurnoDTO(
        id = id,
        uuid = uuid.toString(),
        fechaInicio = fechaInicio.toString(),
        fechaFinal = fechaFinal.toString(),
        usuario = usuario.toString()
    )
}