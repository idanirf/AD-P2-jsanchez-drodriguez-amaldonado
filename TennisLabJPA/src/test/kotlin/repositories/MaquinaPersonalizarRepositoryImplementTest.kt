package repositories

import db.HibernateManager
import db.getUsuariosInit
import models.MaquinaPersonalizar
import models.Turno
import org.junit.jupiter.api.*
import repositories.maquina.MaquinaPersonalizarRepositoryImplement
import repositories.turno.TurnoRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MaquinaPersonalizarRepositoryImplementTest {
    private val maquinaPersonalizarRepositoryImplement: MaquinaPersonalizarRepositoryImplement =
        MaquinaPersonalizarRepositoryImplement()
    private val turnoRepositoryImplement: TurnoRepositoryImplement = TurnoRepositoryImplement()
    private val usuarioRepositoryImplement: UsuarioRepositoryImplement = UsuarioRepositoryImplement()

    private val turno = Turno(
        id = 0,
        uuid = UUID.randomUUID(),
        fechaInicio = LocalDateTime.of(2022, 10, 14, 9, 10),
        fechaFinal = LocalDateTime.of(2022, 11, 20, 10, 20),
        usuario = getUsuariosInit()[4]
    )

    private val maquina = MaquinaPersonalizar(
        id = 0,
        uuid = UUID.randomUUID(),
        marca = "Signum Pro",
        modelo = "30",
        fechaAdquisicion = LocalDate.of(2022, 4, 15),
        numeroSerie = 7,
        swingweight = true,
        balance = 300.80,
        rigidez = 100.60,
        turno = turno
    )

    @AfterEach
    fun tearDown() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM MaquinaPersonalizar")
            query.executeUpdate()
        }
    }

    @BeforeEach
    fun beforeEach() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM MaquinaPersonalizar")
            query.executeUpdate()
        }
        HibernateManager.open()
        HibernateManager.close()
    }

    @BeforeAll
    fun setUp() {
        usuarioRepositoryImplement.save(getUsuariosInit()[4])
        turnoRepositoryImplement.save(turno)
    }

    @Test
    fun findAll() {
        val res = maquinaPersonalizarRepositoryImplement.findAll()

        assert(res.isEmpty())
    }

    @Test
    fun findById() {
        maquinaPersonalizarRepositoryImplement.save(maquina)

        val res = maquinaPersonalizarRepositoryImplement.findById(maquina.id)
        println(res)
        println(maquina)

        assert(res?.id == maquina.id)
    }

    @Test
    fun findByIdNoExiste() {
        val res = maquinaPersonalizarRepositoryImplement.findById(-5)

        assert(res == null)

    }

    @Test
    fun saveInsert() {
        val res = maquinaPersonalizarRepositoryImplement.save(maquina)

        Assertions.assertAll(
            { Assertions.assertEquals(res.id, maquina.id) },
            { Assertions.assertEquals(res.uuid, maquina.uuid) },
            { Assertions.assertEquals(res.marca, maquina.marca) },
            { Assertions.assertEquals(res.modelo, maquina.modelo) },
            { Assertions.assertEquals(res.fechaAdquisicion, maquina.fechaAdquisicion) },
            { Assertions.assertEquals(res.numeroSerie, maquina.numeroSerie) },
            { Assertions.assertEquals(res.turno, maquina.turno) },
            { Assertions.assertEquals(res.swingweight, maquina.swingweight) },
            { Assertions.assertEquals(res.balance, maquina.balance) },
            { Assertions.assertEquals(res.rigidez, maquina.rigidez) }
        )
    }

    @Test
    fun delete() {
        maquinaPersonalizarRepositoryImplement.save(maquina)

        val res = maquinaPersonalizarRepositoryImplement.delete(maquina)

        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = maquinaPersonalizarRepositoryImplement.delete(maquina)

        assert(!res)
    }
}