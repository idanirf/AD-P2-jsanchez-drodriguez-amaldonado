/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package models
import org.hibernate.annotations.Type
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "MaquinaPersonalizar")
@NamedQuery(name = "MaquinaPersonalizar.findAll", query = "SELECT t FROM MaquinaPersonalizar t")
data class MaquinaPersonalizar(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int,
    @Column(name = "uuid")
    @Type(type = "uuid-char")
    val uuid: UUID,
    val marca: String,
    val modelo: String,
    val fechaAdquisicion: LocalDate,
    val numeroSerie: Int,
    val swingweight: Boolean,
    val balance: Double,
    val rigidez: Double,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "turno_id", referencedColumnName = "id", nullable = true)
    val turno: Turno
)