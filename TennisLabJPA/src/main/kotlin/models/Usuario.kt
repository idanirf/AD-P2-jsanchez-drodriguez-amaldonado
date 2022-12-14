package models

import kotlinx.serialization.Serializable
import models.enums.TipoUsuario
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Usuario")
@NamedQuery(name = "Usuario.findAll", query = "SELECT t FROM Usuario t")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int,
    @Column(name="UUID_Usuario")
    @Type(type = "uuid-char")
    val uuid: UUID,
    val nombre: String,
    val apellido: String,
    val email: String,
    val contraseña: String,
    val perfil: TipoUsuario,
)

