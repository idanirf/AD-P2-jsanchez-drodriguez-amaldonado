package repositories

import config.AppConfig
import db.DataBaseManager
import entitities.MaquinasEncordarDAO
import entitities.TurnoDAO
import entitities.UsuariosDAO
import exception.MaquinaEncordarException
import models.*
import models.enums.TipoEncordaje
import models.enums.TipoUsuario
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import repositories.maquina.MaquinaEncordarRepositoryImplement
import repositories.turno.TurnoRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MaquinaEncordarRepositoryImplementTest {
    private val maquinasEncordarRepository: MaquinaEncordarRepositoryImplement = MaquinaEncordarRepositoryImplement(
        MaquinasEncordarDAO, TurnoDAO
    )
    private val turnosRepository: TurnoRepositoryImplement = TurnoRepositoryImplement(TurnoDAO, UsuariosDAO)
    private val usuariosRepository: UsuarioRepositoryImplement = UsuarioRepositoryImplement(UsuariosDAO)

    private val usuario = Usuario(
        id = 0,
        uuid = UUID.randomUUID(),
        nombre = "Alfonso",
        apellido = "Cabello",
        correo = "alfonso@cabello.com",
        password = "1234",
        tipoUsuario = TipoUsuario.USUARIO
    )
    private val turno = Turno(
        id = 0,
        uuid = UUID.randomUUID(),
        fechaInicio = LocalDateTime.of(2022, 10, 14, 9, 10),
        fechaFinal = LocalDateTime.of(2022, 11, 20, 10, 20),
        usuario = usuario
    )
    private val maquinaEncordar = MaquinaEncordar(
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


    private fun saveData() = transaction {
        val usuarioDAO = UsuariosDAO.new(turno.usuario.id) {
            uuid = turno.usuario.uuid
            nombre = turno.usuario.nombre
            apellido = turno.usuario.apellido
            correo = turno.usuario.correo
            password = turno.usuario.password
            tipoUsuario = turno.usuario.tipoUsuario
        }

        val turnoDAO = TurnoDAO.new(turno.id) {
            uuid = turno.uuid
            fechaInicio = turno.fechaInicio
            fechaFinal = turno.fechaFinal
            usuario = usuarioDAO
        }

        MaquinasEncordarDAO.new(maquinaEncordar.id) {
            uuid = maquinaEncordar.uuid
            marca = maquinaEncordar.marca
            modelo = maquinaEncordar.modelo
            fechaAdquisicion = maquinaEncordar.fechaAdquisicion
            numeroSerie = maquinaEncordar.numeroSerie
            turno = turnoDAO
            tipo = maquinaEncordar.tipo.toString()
            tensionMaxima = maquinaEncordar.tensionMaxima
            tensionMinima = maquinaEncordar.tensionMinima
        }
    }

    @BeforeAll
    fun setUp() {
        DataBaseManager.init(AppConfig.DEFAULT)
    }

    @AfterAll
    fun tearDown() {
        DataBaseManager.dropTables()
    }

    @BeforeEach
    fun beforeEach() {
        DataBaseManager.clearTables()
    }

    @Test
    fun findAll() {
        val res = maquinasEncordarRepository.findAll()

        assert(res.isEmpty())
    }

    @Test
    fun findById() = transaction {
        saveData()

        val res = maquinasEncordarRepository.findById(maquinaEncordar.id)
        assert(res.id == maquinaEncordar.id)
    }

    @Test
    fun findByIdNoExiste() {
        assertThrows<MaquinaEncordarException> {
            maquinasEncordarRepository.findById(maquinaEncordar.id)
        }
    }

    @Test
    fun saveInsert() {
        usuariosRepository.save(usuario)
        turnosRepository.save(turno)

        maquinasEncordarRepository.save(maquinaEncordar)

        assertTrue(maquinasEncordarRepository.findAll().size == 1)
    }

    @Test
    fun saveUpdate() = transaction {
        saveData()

        val res = maquinasEncordarRepository.findById(maquinaEncordar.id)
        assert(res.id == maquinaEncordar.id)
    }

    @Test
    fun delete() = transaction {
        saveData()

        maquinasEncordarRepository.delete(maquinaEncordar)
        val list = maquinasEncordarRepository.findAll().size
        assert(list == 0)
    }

    @Test
    fun deleteNoExiste() {
        val res = maquinasEncordarRepository.delete(maquinaEncordar)

        assert(!res)
    }
}