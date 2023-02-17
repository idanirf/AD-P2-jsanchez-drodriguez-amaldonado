package models

import dto.PedidoDTO
import models.enums.TipoEstado
import java.time.LocalDate
import java.util.*

/**
 * Pedido
 *
 * @property id
 * @property uuid
 * @property estado
 * @property fechaEntrada
 * @property fechaSalidaProgramada
 * @property fechaEntrega
 * @property precio
 * @property usuario
 * @constructor Create empty Pedido
 */
data class Pedido(
    val id : Int,
    val uuid: UUID,
    val estado: TipoEstado,
    val fechaEntrada: LocalDate,
    val fechaSalidaProgramada: LocalDate,
    val fechaEntrega: LocalDate,
    val precio: Double,
    val usuario: Usuario,
)