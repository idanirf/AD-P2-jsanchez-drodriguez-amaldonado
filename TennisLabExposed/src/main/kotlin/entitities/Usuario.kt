package entitities

import models.enums.TipoUsuario
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object TablaUsuario : IntIdTable("USUARIO") {
    val uuid = uuid("uuid").uniqueIndex()
    val nombre = varchar("nombre", 50)
    val apellido = varchar("apellido", 50)
    val correo = varchar("correo", 50)
    val password = varchar("contrasena", 255)
    val tipoUsuario = enumeration<TipoUsuario>("perfil")
}

/**
 * Usuarios d a o
 *
 * @constructor
 *
 * @param id
 */
class UsuariosDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UsuariosDAO>(TablaUsuario)

    var uuid by TablaUsuario.uuid
    var nombre by TablaUsuario.nombre
    var apellido by TablaUsuario.apellido
    var correo by TablaUsuario.correo
    var password by TablaUsuario.password
    var tipoUsuario by TablaUsuario.tipoUsuario
}