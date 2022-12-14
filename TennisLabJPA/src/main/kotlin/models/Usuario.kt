package models

import kotlinx.serialization.Serializable
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Usuarios")
@NamedQuery(name = "Usuarios.findAll", query = "SELECT t FROM Usuario t")
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

enum class TipoUsuario(valor: String){
    USUARIO("USUARIO"),
    ADMINISTRADOR("ADMINISTRADOR"),
    ENCORDADOR("ENCORDADOR");
    companion object {
        fun from(tipoUsuario: String): TipoUsuario {
            return when (tipoUsuario.uppercase()) {
                "USUARIO" -> USUARIO
                "ADMINISTRADOR" -> ADMINISTRADOR
                "ENCORDADOR" -> ENCORDADOR
                else -> throw IllegalArgumentException("Usuario no reconocido")
            }
        }
    }
}