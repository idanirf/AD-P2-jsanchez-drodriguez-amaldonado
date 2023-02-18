package repositories

import db.HibernateManager
import models.Pedido
import models.TareaPersonalizacion
import models.Usuario
import models.enums.TipoEstado
import models.enums.TipoUsuario
import org.junit.jupiter.api.*
import repositories.pedido.PedidoRepositoryImplement
import repositories.tareas.TareaPersonalizacionRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDate
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TareaPersonalizacionRepositoryImplementTest {

    private val tareaPersonalizacionRepositoryImplement = TareaPersonalizacionRepositoryImplement()
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
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM TareaPersonalizacion")
            query.executeUpdate()
        }
    }

    @BeforeEach
    fun beforeEach() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM TareaPersonalizacion")
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
        val res = tareaPersonalizacionRepositoryImplement.findAll()
        assert(res.isEmpty())
    }

    @Test
    fun findById() {
        tareaPersonalizacionRepositoryImplement.save(tarea)
        val res = tareaPersonalizacionRepositoryImplement.findById(tarea.id)
        assert(res == tarea)
    }

    @Test
    fun findByIdNoExiste() {
        val res = tareaPersonalizacionRepositoryImplement.findById(-5)
        assert(res == null)

    }

    @Test
    fun saveInsert() {
        val res = tareaPersonalizacionRepositoryImplement.save(tarea)

        assertAll(
            { Assertions.assertEquals(res.id, tarea.id) },
            { Assertions.assertEquals(res.uuid, tarea.uuid) },
            { Assertions.assertEquals(res.rigidez, tarea.rigidez) },
            { Assertions.assertEquals(res.peso, tarea.peso) },
            { Assertions.assertEquals(res.balance, tarea.balance) },
            { Assertions.assertEquals(res.precio, tarea.precio) },

            )
    }

    @Test
    fun delete() {
        tareaPersonalizacionRepositoryImplement.save(tarea)
        val res = tareaPersonalizacionRepositoryImplement.delete(tarea)
        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = tareaPersonalizacionRepositoryImplement.delete(tarea)
        assert(!res)
    }
}