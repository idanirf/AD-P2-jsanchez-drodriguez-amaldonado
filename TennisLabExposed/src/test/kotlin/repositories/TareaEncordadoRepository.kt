package repositories

import config.AppConfig
import db.DataBaseManager
import entitities.PedidosDAO
import entitities.TareasEncordadoDAO
import entitities.UsuariosDAO
import models.Pedido
import models.TareaEncordado
import models.Usuario
import models.enums.NumeroNudos
import models.enums.TipoEstado
import models.enums.TipoUsuario
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import repositories.pedido.PedidoRepositoryImplement
import repositories.tareas.TareaEncordadoRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDate
import java.util.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TareasEncordadoRepositoryTest {
    private val usuariosRepositoryImplement = UsuarioRepositoryImplement(UsuariosDAO)
    private val pedidoRepositoryImplement = PedidoRepositoryImplement(PedidosDAO, UsuariosDAO)
    private val tareaEncordadoRepositoryImplement = TareaEncordadoRepositoryImplement(TareasEncordadoDAO, PedidosDAO)

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

    private val tarea = TareaEncordado(
        id = 0,
        uuid = UUID.randomUUID(),
        precio = 100.50,
        tensionVertical = 22.50,
        cordajeVertical = "Babolat",
        tensionHorizontal = 26.10,
        cordajeHorizontal = "Babolat",
        nudos = NumeroNudos.DOS,
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

        val tareaDAO = TareasEncordadoDAO.new(tarea.id) {
            uuid = tarea.uuid
            precio = tarea.precio
            tensionVertical = tarea.tensionVertical
            cordajeVertical = tarea.cordajeVertical
            tensionHorizontal = tarea.tensionHorizontal
            cordajeHorizontal = tarea.cordajeHorizontal
            nudos = tarea.nudos.toString()
            pedido = pedidoDAO
        }
    }

    @Test
    fun findAll() {
        val res = tareaEncordadoRepositoryImplement.findAll()
        assert(res.isEmpty())
    }

    @Test
    fun findById() = transaction {
        saveData()
        val res = tareaEncordadoRepositoryImplement.findById(tarea.id)
        assert(res == tarea)
    }

    @Test
    fun findByIdNoExiste() {
        assertThrows<IllegalStateException> {
            val res = tareaEncordadoRepositoryImplement.findById(-5)
        }

    }

    @Test
    fun saveInsert() {
        usuariosRepositoryImplement.save(usuario)
        pedidoRepositoryImplement.save(pedido)
        val res = tareaEncordadoRepositoryImplement.save(tarea)

        assertAll(
            { assertEquals(res.id, tarea.id) },
            { assertEquals(res.uuid, tarea.uuid) },
            { assertEquals(res.precio, tarea.precio) },
            { assertEquals(res.tensionHorizontal, tarea.tensionHorizontal) },
            { assertEquals(res.tensionVertical, tarea.tensionVertical) },
            { assertEquals(res.cordajeVertical, tarea.cordajeVertical) },
            { assertEquals(res.cordajeHorizontal, tarea.cordajeHorizontal) },
            { assertEquals(res.nudos, tarea.nudos) },
        )
    }

    @Test
    fun saveUpdate() = transaction {
        saveData()
        val res = tareaEncordadoRepositoryImplement.save(tarea)
        assert(res == tarea)
    }

    @Test
    fun delete() = transaction {
        saveData()
        val res = tareaEncordadoRepositoryImplement.delete(tarea)
        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = tareaEncordadoRepositoryImplement.delete(tarea)
        assert(!res)
    }
}