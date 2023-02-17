package repositories

import config.AppConfig
import db.DataBaseManager
import entitities.PedidosDAO
import entitities.UsuariosDAO
import exception.PedidoException
import models.Pedido
import models.Usuario
import models.enums.TipoEstado
import models.enums.TipoUsuario
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import repositories.pedido.PedidoRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDate
import java.util.*
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PedidoRepositoryImplementTest {
    private val pedidosRepositoryImplement: PedidoRepositoryImplement = PedidoRepositoryImplement(PedidosDAO, UsuariosDAO)
    private val usuariosRepositoryImplement: UsuarioRepositoryImplement = UsuarioRepositoryImplement(UsuariosDAO)



    private val usuario = Usuario(
        id = 0,
        uuid = UUID.randomUUID(),
        nombre = "Alfonso",
        apellido = "Cabello",
        correo = "alfonso@cabello.com",
        password = "1234",
        tipoUsuario = TipoUsuario.USUARIO
    )
    private val pedido = Pedido(
        id = 2,
        uuid = UUID.randomUUID(),
        estado = TipoEstado.RECIBIDO,
        fechaEntrada = LocalDate.of(2022, 4, 15),
        fechaSalidaProgramada = LocalDate.of(2022, 9, 10),
        fechaEntrega = LocalDate.of(2022, 10, 10),
        precio = 10.20,
        usuario = usuario
    )
    private fun saveData() = transaction {
        val usuarioDAO = UsuariosDAO.new(pedido.usuario.id) {
            uuid = pedido.usuario.uuid
            nombre = pedido.usuario.nombre
            apellido = pedido.usuario.apellido
            correo = pedido.usuario.correo
            password = pedido.usuario.password
            tipoUsuario = pedido.usuario.tipoUsuario

        }

        PedidosDAO.new(pedido.id) {
            uuid = pedido.uuid
            estado = pedido.estado.toString()
            fechaEntrada = pedido.fechaEntrada
            fechaSalidaProgramada = pedido.fechaSalidaProgramada
            fechaEntrega = pedido.fechaEntrega
            precio = pedido.precio
            usuario = usuarioDAO
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
        val res = pedidosRepositoryImplement.findAll()
        assert(res.isEmpty())
    }

    @Test
    fun findById() = transaction {
        saveData()
        val res = pedidosRepositoryImplement.findById(pedido.id)
        assert(res == pedido)
    }

    @Test
    fun findByIdNoExiste() {
        assertThrows<PedidoException> {
            pedidosRepositoryImplement.findById(pedido.id)
        }
    }

    @Test
    fun saveInsert() {
        usuariosRepositoryImplement.save(usuario)
        val res = pedidosRepositoryImplement.save(pedido)
        assert(pedidosRepositoryImplement.findAll()[0] == res)
    }

    @Test
    fun saveUpdate() = transaction {
        saveData()
        val res = pedidosRepositoryImplement.findById(pedido.id)
        assert(res == pedido)
    }

    @Test
    fun delete() = transaction {
        saveData()
        val res = pedidosRepositoryImplement.delete(pedido)
        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = pedidosRepositoryImplement.delete(pedido)
        assert(!res)
    }
}