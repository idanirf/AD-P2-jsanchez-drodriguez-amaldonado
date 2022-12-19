package repositories

import db.HibernateManager
import models.Pedido
import models.Usuario
import models.enums.TipoEstado
import models.enums.TipoUsuario
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import repositories.pedido.PedidoRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDate
import java.util.*
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class PedidoRepositoryImplementTest {
    private val pedidoRepository: PedidoRepositoryImplement = PedidoRepositoryImplement()
    private val usuarioRepositoryImplement: UsuarioRepositoryImplement = UsuarioRepositoryImplement()

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
        estado = TipoEstado.TERMINADO,
        fechaEntrada = LocalDate.of(2022, 4, 15),
        fechaSalidaProgramada = LocalDate.of(2022, 9, 10),
        fechaEntrega = LocalDate.of(2022, 10, 10),
        precio = 10.20,
        usuario = usuario
    )

    @AfterEach
    fun tearDown() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM Turnos")
            query.executeUpdate()
        }
    }

    @BeforeEach
    fun beforeEach() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM Turnos")
            query.executeUpdate()
        }
        HibernateManager.open()
        HibernateManager.close()
    }

    @Test
    fun findAll() {
        val res = pedidoRepository.findAll()

        assert(res.isEmpty())
    }

    @Test
    fun findById() {
        pedidoRepository.save(pedido)

        val res = pedidoRepository.findById(pedido.id)

        assert(res == pedido)
    }

    @Test
    fun findByIdNoExiste() {
        val res = pedidoRepository.findById(-5)

        assert(res == null)

    }

    @Test
    fun saveInsert() {
        usuarioRepositoryImplement.save(usuario)
        val res = pedidoRepository.save(pedido)

        assertAll(
            { Assertions.assertEquals(res.id, pedido.id) },
            { Assertions.assertEquals(res.uuid, pedido.uuid) },
            { Assertions.assertEquals(res.estado, pedido.estado) },
            { Assertions.assertEquals(res.fechaEntrada, pedido.fechaEntrada) },
            { Assertions.assertEquals(res.fechaSalidaProgramada, pedido.fechaSalidaProgramada) },
            { Assertions.assertEquals(res.fechaEntrega, pedido.fechaEntrega) },
            { Assertions.assertEquals(res.precio, pedido.precio) }
        )
    }

    @Test
    fun delete() {
        usuarioRepositoryImplement.save(usuario)
        pedidoRepository.save(pedido)

        val res = pedidoRepository.delete(pedido)

        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = pedidoRepository.delete(pedido)

        assert(!res)
    }

}