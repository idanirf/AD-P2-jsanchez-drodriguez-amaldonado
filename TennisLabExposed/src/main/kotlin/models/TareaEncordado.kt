package models

import models.enums.NumeroNudos
import java.util.*

/**
 * Tarea encordado
 *
 * @property id
 * @property uuid
 * @property precio
 * @property tensionVertical
 * @property cordajeVertical
 * @property tensionHorizontal
 * @property cordajeHorizontal
 * @property nudos
 * @property pedido
 * @constructor Create empty Tarea encordado
 */
data class TareaEncordado(
    val id: Int,
    val uuid: UUID,
    val precio: Double,
    val tensionVertical: Double,
    val cordajeVertical: String,
    val tensionHorizontal: Double,
    val cordajeHorizontal: String,
    val nudos: NumeroNudos,
    val pedido: Pedido,
)
