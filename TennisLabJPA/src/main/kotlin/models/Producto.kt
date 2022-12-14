package models

import models.enums.TipoProducto
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*
@Entity
@Table(name = "Producto")
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
data class Producto(
    @Id
    val id : Int,
    @Column(name = "uuid")
    @Type(type = "uuid-char")
    val uuid: UUID,
    val marca: String,
    val modelo: String,
    val precio: Double,
    val stock: Int,
    val tipoProducto: TipoProducto,
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = true)
    val pedido: Pedido
)
