package repositories

import config.AppConfig
import db.DataBaseManager
import entitities.UsuariosDAO
import exception.UsuarioException
import models.Usuario
import models.enums.TipoUsuario
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import repositories.usuario.UsuarioRepositoryImplement
import java.util.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioRepositoryImplementTest {
    private val usuariosRepository = UsuarioRepositoryImplement(UsuariosDAO)

    private val usuario = Usuario(
        id = 0,
        uuid = UUID.randomUUID(),
        nombre = "Alfonso",
        apellido = "Cabello",
        correo = "alfonso@cabello.com",
        password = "1234",
        tipoUsuario = TipoUsuario.USUARIO
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

    @Test
    fun findAll() {
        val res = usuariosRepository.findAll()

        assert(res.isEmpty())
    }

    @Test
    fun findById() = transaction {
        UsuariosDAO.new(usuario.id) {
            uuid = usuario.uuid
            nombre = usuario.nombre
            apellido = usuario.apellido
            correo = usuario.correo
            password = usuario.password
            tipoUsuario = usuario.tipoUsuario

        }

        val res = usuariosRepository.findById(usuario.id)

        assert(res == usuario)
    }

    @Test
    fun findByIdNoExiste() {
        assertThrows<UsuarioException> {
            val res = usuariosRepository.findById(-5)
        }

    }

    @Test
    fun saveInsert() {
        val res = usuariosRepository.save(usuario)

        assertAll(
            { assertEquals(res.id, usuario.id) },
            { assertEquals(res.uuid, usuario.uuid) },
            { assertEquals(res.nombre, usuario.nombre) },
            { assertEquals(res.apellido, usuario.apellido) },
            { assertEquals(res.correo, usuario.correo) },
            { assertEquals(res.tipoUsuario, usuario.tipoUsuario) },
        )
    }

    @Test
    fun saveUpdate() = transaction {
        UsuariosDAO.new(usuario.id) {
            uuid = usuario.uuid
            nombre = usuario.nombre
            apellido = usuario.apellido
            correo = usuario.correo
            password = usuario.password
            tipoUsuario = usuario.tipoUsuario
        }

        val res = usuariosRepository.save(usuario)

        assert(res == usuario)
    }

    @Test
    fun delete() = transaction {
        UsuariosDAO.new(usuario.id) {
            uuid = usuario.uuid
            nombre = usuario.nombre
            apellido = usuario.apellido
            correo = usuario.correo
            password = usuario.password
            tipoUsuario = usuario.tipoUsuario
        }

        val res = usuariosRepository.delete(usuario)

        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = usuariosRepository.delete(usuario)

        assert(!res)
    }
}