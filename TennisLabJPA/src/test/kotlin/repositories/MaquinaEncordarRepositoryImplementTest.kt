package repositories

import db.HibernateManager
import db.getUsuariosInit
import models.MaquinaEncordar
import models.Turno
import models.enums.TipoEncordaje
import org.junit.jupiter.api.*
import repositories.maquina.MaquinaEncordarRepositoryImplement
import repositories.turno.TurnoRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MaquinaEncordarRepositoryImplementTest {
    private val maquinaEncordarRepositoryImplement: MaquinaEncordarRepositoryImplement = MaquinaEncordarRepositoryImplement()
    private val turnoRepositoryImplement: TurnoRepositoryImplement = TurnoRepositoryImplement()
    private val usuarioRepositoryImplement: UsuarioRepositoryImplement = UsuarioRepositoryImplement()

    private val turno = Turno(
        id = 0,
        uuid = UUID.randomUUID(),
        fechaInicio = LocalDateTime.of(2022, 10, 14, 9, 10),
        fechaFinal = LocalDateTime.of(2022, 11, 20, 10, 20),
        usuario = getUsuariosInit()[2]
    )
    private val maquina = MaquinaEncordar(
        id = 0,
        uuid = UUID.randomUUID(),
        marca = "HEAD",
        modelo = "2020",
        fechaAdquisicion = LocalDate.of(2022, 3, 5),
        numeroSerie = 20,
        tipo = TipoEncordaje.MANUAL,
        tensionMaxima = 25.10,
        tensionMinima = 22.60,
        turno = turno
    )

    @AfterEach
    fun tearDown() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM MaquinasEncordar")
            query.executeUpdate()
        }
    }

    @BeforeEach
    fun beforeEach() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM MaquinasEncordar")
            query.executeUpdate()
        }
        HibernateManager.open()
        HibernateManager.close()
    }

    @BeforeAll
    fun setUp() {
        usuarioRepositoryImplement.save(getUsuariosInit()[2])
        turnoRepositoryImplement.save(turno)
    }

    @Test
    fun findAll() {
        val res = maquinaEncordarRepositoryImplement.findAll()

        assert(res.isEmpty())
    }

    @Test
    fun findById() {
        maquinaEncordarRepositoryImplement.save(maquina)

        val res = maquinaEncordarRepositoryImplement.findById(maquina.id)

        assert(res?.id == maquina.id)
    }

    @Test
    fun findByIdNoExiste() {
        val res = maquinaEncordarRepositoryImplement.findById(-5)

        assert(res == null)

    }

    @Test
    fun saveInsert() {
        val res = maquinaEncordarRepositoryImplement.save(maquina)

        Assertions.assertAll(
            { Assertions.assertEquals(res.id, maquina.id) },
            { Assertions.assertEquals(res.uuid, maquina.uuid) },
            { Assertions.assertEquals(res.marca, maquina.marca) },
            { Assertions.assertEquals(res.modelo, maquina.modelo) },
            { Assertions.assertEquals(res.fechaAdquisicion, maquina.fechaAdquisicion) },
            { Assertions.assertEquals(res.numeroSerie, maquina.numeroSerie) },
            { Assertions.assertEquals(res.tipo, maquina.tipo) },
            { Assertions.assertEquals(res.tensionMaxima, maquina.tensionMaxima) },
            { Assertions.assertEquals(res.tensionMinima, maquina.tensionMinima) },
            { Assertions.assertEquals(res.turno, maquina.turno) }
        )
    }

    @Test
    fun delete() {
        maquinaEncordarRepositoryImplement.save(maquina)

        val res = maquinaEncordarRepositoryImplement.delete(maquina)

        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = maquinaEncordarRepositoryImplement.delete(maquina)

        assert(!res)
    }
}