/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package dto

import kotlinx.serialization.Serializable
import models.Usuario

@Serializable
data class UsuarioDTO(
    val id : Int,
    val uuid: String,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val password: String,
    val tipoUsuario: String
)

fun Usuario.toDTO() : UsuarioDTO{
    return UsuarioDTO(
        id = id,
        uuid = uuid.toString(),
        nombre = nombre,
        apellido = apellido,
        correo = correo,
        password = password,
        tipoUsuario = tipoUsuario.toString()
    )
}