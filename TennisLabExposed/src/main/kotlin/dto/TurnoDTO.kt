package dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import models.Turno
import models.Usuario
import java.time.LocalDateTime
import java.util.*

/**
 * Turno d t o
 *
 * @property id
 * @property uuid
 * @property fechaInicio
 * @property fechaFinal
 * @property usuario
 * @constructor Create empty Turno d t o
 */
@Serializable
@SerialName("Turno")
data class TurnoDTO (
    val id: Int,
    val uuid: String,
    val fechaInicio: String,
    val fechaFinal: String,
    val usuario: String
)

/**
 * To d t o
 *
 * @return
 */
fun Turno.toDTO(): TurnoDTO{
    return TurnoDTO(
        id=id,
        uuid=uuid.toString(),
        fechaInicio=fechaInicio.toString(),
        fechaFinal=fechaFinal.toString(),
        usuario = usuario.toString()
    )
}