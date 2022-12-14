package models
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "Turnos")
@NamedQuery(name = "Turnos.findAll", query = "SELECT t FROM Turno t")
data class Turno(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Column(name = "uuid")
    @Type(type = "uuid-char")
    val uuid: UUID,
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    val fechaInicio: LocalDateTime,
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    val fechaFinal: LocalDateTime,
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    val usuario: Usuario
)