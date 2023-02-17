package models

import models.enums.TipoUsuario
import java.util.*

/**
 * Usuario
 *
 * @property id
 * @property uuid
 * @property nombre
 * @property apellido
 * @property correo
 * @property password
 * @property tipoUsuario
 * @constructor Create empty Usuario
 */
data class Usuario(
    val id:Int,
    val uuid: UUID,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val password: String,
    val tipoUsuario: TipoUsuario
)