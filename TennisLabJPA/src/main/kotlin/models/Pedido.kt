/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package models

import models.enums.TipoEstado
import org.hibernate.annotations.Type
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Pedido")
@NamedQuery(name = "Pedido.findAll", query = "SELECT t FROM Pedido t")
data class Pedido(
    @Id
    val id : Int,
    @Column(name = "uuid")
    @Type(type = "uuid-char")
    val uuid: UUID,
    val estado: TipoEstado,
    val fechaEntrada: LocalDate,
    val fechaSalidaProgramada: LocalDate,
    val fechaEntrega: LocalDate,
    val precio: Double,
    @OneToOne
    @JoinColumn(name = "encordador_id", referencedColumnName = "id", nullable = true)
    val usuario: Usuario,
)
