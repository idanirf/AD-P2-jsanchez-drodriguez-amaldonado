package mapper


import entitities.TurnoDAO
import models.Turno

/**
 * From turno d a o to turno
 *
 * @return El objeto ya en modelo.
 */
fun TurnoDAO.fromTurnoDAOToTurno(): Turno {
    return Turno(
        id = id.value,
        uuid = uuid,
        fechaInicio = fechaInicio,
        fechaFinal = fechaFinal,
        usuario = usuario.fromUsuarioDAOToUsuario()
    )
}