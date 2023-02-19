package mapper

import entitities.UsuariosDAO
import models.Usuario

/**
 * From usuario d a o to usuario
 *
 * @return El objeto ya en modelo.
 */
fun UsuariosDAO.fromUsuarioDAOToUsuario(): Usuario {
    return Usuario(
        id = id.value,
        uuid = uuid,
        nombre = nombre,
        apellido = apellido,
        correo = correo,
        password = password,
        tipoUsuario = tipoUsuario,
    )
}