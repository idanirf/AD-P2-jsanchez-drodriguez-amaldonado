package repositories

import db.HibernateManager
import models.Pedido
import models.TareaEncordado
import models.Usuario
import models.enums.NumeroNudos
import models.enums.TipoEstado
import models.enums.TipoUsuario
import org.junit.jupiter.api.*
import repositories.pedido.PedidoRepositoryImplement
import repositories.tareas.TareaEncordadoRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDate
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TareaEncordarRepositoryImplementTest {
    private val tareasEncordadoRepositoryImplement = TareaEncordadoRepositoryImplement()
    private val usuarioRepositoryImplement = UsuarioRepositoryImplement()
    private val pedidoRepositoryImplement = PedidoRepositoryImplement()

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
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM TareaEncordado")
            query.executeUpdate()
        }
    }

    @BeforeEach
    fun beforeEach() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM TareaEncordado")
            query.executeUpdate()
        }

    }

    @BeforeAll
    fun setUp() {
        usuarioRepositoryImplement.save(usuario)
        pedidoRepositoryImplement.save(pedido)
    }

    @Test
    fun findAll() {
        val res = tareasEncordadoRepositoryImplement.findAll()

        assert(res.isEmpty())
    }

    @Test
    fun findById() {
        tareasEncordadoRepositoryImplement.save(tarea)

        val res = tareasEncordadoRepositoryImplement.findById(tarea.id)

        assert(res == tarea)
    }

    @Test
    fun findByIdNoExiste() {
        val res = tareasEncordadoRepositoryImplement.findById(-5)

        assert(res == null)

    }

    @Test
    fun saveInsert() {
        val res = tareasEncordadoRepositoryImplement.save(tarea)

        assertAll(
            { Assertions.assertEquals(res.id, tarea.id) },
            { Assertions.assertEquals(res.uuid, tarea.uuid) },
            { Assertions.assertEquals(res.precio, tarea.precio) },
            { Assertions.assertEquals(res.tensionVertical, tarea.tensionVertical) },
            { Assertions.assertEquals(res.cordajeVertical, tarea.cordajeVertical) },
            { Assertions.assertEquals(res.tensionHorizontal, tarea.tensionHorizontal) },
            { Assertions.assertEquals(res.cordajeHorizontal, tarea.cordajeHorizontal) },
            { Assertions.assertEquals(res.nudos, tarea.nudos) },
        )
    }

    @Test
    fun delete() {
        tareasEncordadoRepositoryImplement.save(tarea)

        val res = tareasEncordadoRepositoryImplement.delete(tarea)

        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = tareasEncordadoRepositoryImplement.delete(tarea)

        assert(!res)
    }
}