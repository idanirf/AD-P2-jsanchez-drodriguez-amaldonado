package models

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*
@Entity
@Table(name = "PRODUCTO")
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
data class Producto(
    @Id
    val id : Int,
    @Column(name = "uuid")
    @Type(type = "uuid-char")
    val uuid: UUID,
    val tipoProducto: TipoProducto,
    val marca: String,
    val modelo: String,
    val precio: Double,
    val stock: Int,
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = true)
    val pedido: Pedido
)

enum class TipoProducto(val tipoProducto: String) {
    RAQUETA("RAQUETA"),
    CORDAJE("CORDAJE"), COMPLEMENTO("COMPLEMENTO");
    companion object {
        fun from(producto: String): TipoProducto {
            return when (producto.uppercase()) {
                "RAQUETA" -> RAQUETA
                "CORDAJE" -> CORDAJE
                "COMPLEMENTO" -> COMPLEMENTO
                else -> throw IllegalArgumentException("Producto no válido.")
            }
        }
    }
}