package models

import java.time.LocalDateTime
import java.util.*

/**
 * Turno
 *
 * @property id
 * @property uuid
 * @property fechaInicio
 * @property fechaFinal
 * @property usuario
 * @constructor Create empty Turno
 */
data class Turno(
    val id: Int,
    val uuid: UUID,
    val fechaInicio: LocalDateTime,
    val fechaFinal: LocalDateTime,
    val usuario: Usuario
)
