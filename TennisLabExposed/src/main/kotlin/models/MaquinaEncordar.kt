package models

import models.enums.TipoEncordaje
import java.time.LocalDate
import java.util.*

/**
 * Maquina encordar
 *
 * @property id
 * @property uuid
 * @property marca
 * @property modelo
 * @property fechaAdquisicion
 * @property numeroSerie
 * @property tipo
 * @property tensionMaxima
 * @property tensionMinima
 * @property turno
 * @constructor Create empty Maquina encordar
 */
data class MaquinaEncordar(
    val id: Int,
    val uuid: UUID,
    val marca: String,
    val modelo: String,
    val fechaAdquisicion: LocalDate,
    val numeroSerie: Int,
    val tipo: TipoEncordaje,
    val tensionMaxima: Double,
    val tensionMinima: Double,
    val turno: Turno
)
