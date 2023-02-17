package models

import java.util.*

/**
 * Tarea personalizacion
 *
 * @property id
 * @property uuid
 * @property rigidez
 * @property peso
 * @property balance
 * @property precio
 * @property pedido
 * @constructor Create empty Tarea personalizacion
 */
data class TareaPersonalizacion(
    val id: Int,
    val uuid: UUID,
    val rigidez: Double,
    val peso: Double,
    val balance: Double,
    val precio: Double,
    val pedido: Pedido,
)