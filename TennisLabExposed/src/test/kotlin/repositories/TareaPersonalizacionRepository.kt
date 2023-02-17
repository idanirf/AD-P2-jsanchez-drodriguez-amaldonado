package repositories

import config.AppConfig
import db.DataBaseManager
import entitities.PedidosDAO
import entitities.TareasPersonalizacionDAO
import entitities.UsuariosDAO
import models.Pedido
import models.TareaPersonalizacion
import models.Usuario
import models.enums.TipoEstado
import models.enums.TipoUsuario
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import repositories.pedido.PedidoRepositoryImplement
import repositories.tareas.TareaPersonalizacionRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDate
import java.util.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TareasPersonalizacionRepositoryTest {
    private val usuariosRepositoryImplement = UsuarioRepositoryImplement(UsuariosDAO)
    private val pedidoRepositoryImplement = PedidoRepositoryImplement(PedidosDAO, UsuariosDAO)
    private val tareaPersonalizacionRepositoryImplement =
        TareaPersonalizacionRepositoryImplement(TareasPersonalizacionDAO, PedidosDAO)


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

    private val tarea = TareaPersonalizacion(
        id = 0,
        uuid = UUID.randomUUID(),
        rigidez = 69.69,
        peso = 0.20,
        balance = 327.10,
        precio = 99.99,
        pedido = pedido
    )

    @AfterEach
    fun tearDown() {
        DataBaseManager.dropTables()
    }

    @BeforeEach
    fun beforeEach() {
        DataBaseManager.init(AppConfig.DEFAULT)
        DataBaseManager.clearTables()
    }

    private fun saveData() = transaction {
        val usuarioDAO = UsuariosDAO.new(pedido.usuario.id) {
            uuid = pedido.usuario.uuid
            nombre = pedido.usuario.nombre
            apellido = pedido.usuario.apellido
            correo = pedido.usuario.correo
            password = pedido.usuario.password
            tipoUsuario = pedido.usuario.tipoUsuario

        }

        val pedidoDAO = PedidosDAO.new(pedido.id) {
            uuid = pedido.uuid
            estado = pedido.estado.toString()
            fechaEntrada = pedido.fechaEntrada
            fechaSalidaProgramada = pedido.fechaSalidaProgramada
            fechaEntrega = pedido.fechaEntrega
            usuario = usuarioDAO
            precio = pedido.precio
        }

        val tareaDAO = TareasPersonalizacionDAO.new(tarea.id) {
            uuid = tarea.uuid
            precio = tarea.precio
            pedido = pedidoDAO
            peso = tarea.peso
            balance = tarea.balance
            rigidez = tarea.rigidez
        }
    }

    @Test
    fun findAll() {
        val res = tareaPersonalizacionRepositoryImplement.findAll()

        assert(res.isEmpty())
    }

    @Test
    fun findById() = transaction {
        saveData()

        val res = tareaPersonalizacionRepositoryImplement.findById(tarea.id)

        assert(res == tarea)
    }

    @Test
    fun findByIdNoExiste() {
        assertThrows<IllegalStateException> {
            val res = tareaPersonalizacionRepositoryImplement.findById(-10)
        }
    }

    @Test
    fun saveInsert() {
        usuariosRepositoryImplement.save(usuario)
        pedidoRepositoryImplement.save(pedido)
        val res = tareaPersonalizacionRepositoryImplement.save(tarea)

        assertAll(
            { assertEquals(res.id, tarea.id) },
            { assertEquals(res.uuid, tarea.uuid) },
            { assertEquals(res.precio, tarea.precio) },
            { assertEquals(res.peso, tarea.peso) },
            { assertEquals(res.balance, tarea.balance) },
            { assertEquals(res.rigidez, tarea.rigidez) },
        )
    }

    @Test
    fun saveUpdate() = transaction {
        saveData()
        val res = tareaPersonalizacionRepositoryImplement.save(tarea)
        assert(res == tarea)
    }

    @Test
    fun delete() = transaction {
        saveData()
        val res = tareaPersonalizacionRepositoryImplement.delete(tarea)
        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = tareaPersonalizacionRepositoryImplement.delete(tarea)
        assert(!res)
    }
}