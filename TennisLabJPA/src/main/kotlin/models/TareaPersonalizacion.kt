package models

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "TareaPersonalizacion")
@NamedQuery(name = "TareaPersonalizacion.findAll", query = "SELECT t FROM TareaPersonalizacion t")
data class TareaPersonalizacion(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Column(name = "uuid")
    @Type(type = "uuid-char")
    val uuid: UUID,
    val rigidez: Double,
    val peso: Double,
    val balance: Double,
    val precio: Double,
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
    val pedido: Pedido,
)