package models

import java.time.LocalDate
import java.util.*

/**
 * Maquina personalizar
 *
 * @property id
 * @property uuid
 * @property marca
 * @property modelo
 * @property fechaAdquisicion
 * @property numeroSerie
 * @property swingweight
 * @property balance
 * @property rigidez
 * @property turno
 * @constructor Create empty Maquina personalizar
 */
data class MaquinaPersonalizar(
    val id: Int,
    val uuid: UUID,
    val marca: String,
    val modelo: String,
    val fechaAdquisicion: LocalDate,
    val numeroSerie: Int,
    val swingweight: Boolean,
    val balance: Double,
    val rigidez: Double,
    val turno: Turno
)
