/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package models

import models.enums.NumeroNudos
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "TareaEncordado")
@NamedQuery(name = "TareaEncordado.findAll", query = "SELECT t FROM TareaEncordado t")
data class TareaEncordado(
    @Id
    val id: Int,
    @Column(name = "uuid")
    @Type(type = "uuid-char")
    val uuid: UUID,
    val precio: Double,
    val tensionVertical: Double,
    val cordajeVertical: String,
    val tensionHorizontal: Double,
    val cordajeHorizontal: String,
    val nudos: NumeroNudos,
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
    val pedido: Pedido,
)
