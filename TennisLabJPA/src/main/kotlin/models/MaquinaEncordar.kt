/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package models

import models.enums.TipoEncordaje
import org.hibernate.annotations.Type
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "MaquinasEncordar")
@NamedQuery(name = "MaquinaEncordar.findAll", query = "SELECT t FROM MaquinaEncordar t")
data class MaquinaEncordar(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Column(name = "uuid")
    @Type(type = "uuid-char")
    val uuid: UUID,
    val marca: String,
    val modelo: String,
    val fechaAdquisicion: LocalDate,
    val numeroSerie: Int,
    val tipo: TipoEncordaje,
    val tensionMaxima: Double,
    val tensionMinima: Double,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "turno_id", referencedColumnName = "id", nullable = false)
    val turno: Turno


    )
