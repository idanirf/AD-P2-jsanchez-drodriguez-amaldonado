package repositories

import config.AppConfig
import db.DataBaseManager
import db.getUsuariosInit
import entitities.TurnoDAO
import entitities.UsuariosDAO
import exception.TurnoException
import models.Turno
import models.Usuario
import models.enums.TipoUsuario
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import repositories.turno.TurnoRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDateTime
import java.util.*
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TurnoRepositoryImplementTest {
    private val turnosRepository: TurnoRepositoryImplement = TurnoRepositoryImplement(TurnoDAO, UsuariosDAO)
    private val usuariosRepository: UsuarioRepositoryImplement = UsuarioRepositoryImplement(UsuariosDAO)
    private val usuario = Usuario(
        id = 2,
        uuid = UUID.randomUUID(),
        nombre = "Alfonso",
        apellido = "Cabello",
        correo = "alfonso@cabello.com",
        password = "1234",
        tipoUsuario = TipoUsuario.USUARIO
    )

    private val turno = Turno(
        id = 1,
        uuid = UUID.randomUUID(),
        fechaInicio = LocalDateTime.of(2022, 10, 14, 9, 10),
        fechaFinal = LocalDateTime.of(2022, 11, 20, 10, 20),
        usuario = usuario
    )
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

    private fun saveData() = transaction {
        val usuarioDAO = UsuariosDAO.new(turno.usuario.id) {
            uuid = turno.usuario.uuid
            nombre = turno.usuario.nombre
            apellido = turno.usuario.apellido
            correo = turno.usuario.correo
            password = turno.usuario.password
            tipoUsuario = turno.usuario.tipoUsuario
        }

         TurnoDAO.new(turno.id) {
            uuid = turno.uuid
            fechaInicio = turno.fechaInicio
            fechaFinal = turno.fechaFinal
            usuario = usuarioDAO
        }
    }

    @Test
    fun findAll() {
        val res = turnosRepository.findAll()

        assert(res.isEmpty())
    }

    @Test
    fun findById() = transaction {
        saveData()

        val res = turnosRepository.findById(turno.id)
        assert(res == turno)
    }

    @Test
    fun findByIdNoExiste() {
        assertThrows<TurnoException> {
            turnosRepository.findById(turno.id)
        }
    }

    @Test
    fun saveInsert() {
        usuariosRepository.save(getUsuariosInit()[2])
        val res = turnosRepository.save(turno)

        assert(turnosRepository.findAll()[0] == res)
    }

    @Test
    fun saveUpdate() = transaction {
        saveData()

        val res = turnosRepository.save(turno)

        assert(res == turno)
    }

    @Test
    fun delete() = transaction {
        saveData()

        val res = turnosRepository.delete(turno)

        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = turnosRepository.delete(turno)

        assert(!res)
    }
}