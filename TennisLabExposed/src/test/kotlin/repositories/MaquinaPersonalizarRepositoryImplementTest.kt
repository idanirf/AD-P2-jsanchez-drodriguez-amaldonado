package repositories

import config.AppConfig
import db.DataBaseManager
import entitities.MaquinaPersonalizarDAO
import entitities.TurnoDAO
import entitities.UsuariosDAO
import exception.MaquinaPersonalizacionException
import models.MaquinaPersonalizar
import models.Turno
import models.Usuario
import models.enums.TipoUsuario
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import repositories.maquina.MaquinaPersonalizarRepositoryImplement
import repositories.turno.TurnoRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MaquinaPersonalizarRepositoryImplementTest {
    private val maquinasPersonalizarRepository: MaquinaPersonalizarRepositoryImplement =
        MaquinaPersonalizarRepositoryImplement(
            MaquinaPersonalizarDAO, TurnoDAO
        )
    private val turnosRepository: TurnoRepositoryImplement = TurnoRepositoryImplement(TurnoDAO, UsuariosDAO)
    private val usuariosRepository: UsuarioRepositoryImplement = UsuarioRepositoryImplement(UsuariosDAO)

    private val usuario = Usuario(
        id = 1,
        uuid = UUID.randomUUID(),
        nombre = "Marcelo",
        apellido = "Alvarez",
        correo = "marcelo@alvarez.com",
        password = "1234",
        tipoUsuario = TipoUsuario.ADMINISTRADOR
    )

    private val turno = Turno(
        id = 0,
        uuid = UUID.randomUUID(),
        fechaInicio = LocalDateTime.of(2022, 10, 14, 9, 10),
        fechaFinal = LocalDateTime.of(2022, 11, 20, 10, 20),
        usuario = usuario
    )

    private val maquinaPersonalizar = MaquinaPersonalizar(
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

        MaquinaPersonalizarDAO.new(maquinaPersonalizar.id) {
            uuid = maquinaPersonalizar.uuid
            marca = maquinaPersonalizar.marca
            modelo = maquinaPersonalizar.modelo
            fechaAdquisicion = maquinaPersonalizar.fechaAdquisicion
            numeroSerie = maquinaPersonalizar.numeroSerie
            swingweight = maquinaPersonalizar.swingweight
            balance = maquinaPersonalizar.balance
            rigidez = maquinaPersonalizar.rigidez
            turno = turnoDAO


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
        val res = maquinasPersonalizarRepository.findAll()
        assert(res.isEmpty())
    }

    @Test
    fun findById() = transaction {
        saveData()
        val res = maquinasPersonalizarRepository.findById(maquinaPersonalizar.id)
        assert(res.id == maquinaPersonalizar.id)
    }

    @Test
    fun findByIdNoExiste() {
        assertThrows<MaquinaPersonalizacionException> {
            maquinasPersonalizarRepository.findById(maquinaPersonalizar.id)
        }
    }

    @Test
    fun saveInsert() {
        usuariosRepository.save(usuario)
        turnosRepository.save(turno)
        val res = maquinasPersonalizarRepository.save(maquinaPersonalizar)
        Assertions.assertTrue(maquinasPersonalizarRepository.findAll().size == 1)
    }

    @Test
    fun saveUpdate() = transaction {
        saveData()
        val res = maquinasPersonalizarRepository.findById(maquinaPersonalizar.id)
        assert(res.id == maquinaPersonalizar.id)
    }

    @Test
    fun delete() = transaction {
        saveData()
        val res = maquinasPersonalizarRepository.delete(maquinaPersonalizar)
        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = maquinasPersonalizarRepository.delete(maquinaPersonalizar)
        assert(!res)
    }
}