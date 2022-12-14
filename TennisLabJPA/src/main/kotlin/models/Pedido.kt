package models

import org.hibernate.annotations.Type
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Pedido")
@NamedQuery(name = "Pedido.findAll", query = "SELECT t FROM Pedido t")
data class Pedido(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int,
    @Column(name = "uuid")
    @Type(type = "uuid-char")
    val uuid: UUID,
    val estado: TipoEstado,
    @Type(type = "org.hibernate.type.LocalDateType")
    val fechaEntrada: LocalDate,
    @Type(type = "org.hibernate.type.LocalDateType")
    val fechaSalidaProgramada: LocalDate,
    @Type(type = "org.hibernate.type.LocalDateType")
    val fechaEntrega: LocalDate,
    val precio: Double,
    @OneToOne
    @JoinColumn(name = "encordador_id", referencedColumnName = "id", nullable = true)
    val usuario: Usuario,
)

enum class TipoEstado(val est: String) {
    RECIBIDO("RECIBIDO"),
    EN_PROCESO("EN PROCESO"),
    TERMINADO("TERMINADO");

    companion object {
        fun from(estado: String): TipoEstado {
            return when (estado.uppercase()) {
                "RECIBIDO" -> RECIBIDO
                "EN_PROCESO" -> EN_PROCESO
                "TERMINADO" -> TERMINADO
                else -> throw IllegalArgumentException("Estado no reconocido.")
            }
        }
    }
}