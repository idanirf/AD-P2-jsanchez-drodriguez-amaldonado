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
    private val pedidoRepositoryImplement: PedidoRepositoryImplement = PedidoRepositoryImplement()
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
    fun eliminar() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM Pedido")
            query.executeUpdate()
        }
    }

    @BeforeEach
    fun porCadaUna() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM Pedido")
            query.executeUpdate()
        }
        HibernateManager.open()
        HibernateManager.close()
    }

    @Test
    fun findAll() {
        val res = pedidoRepositoryImplement.findAll()

        assert(res.isEmpty())
    }

    @Test
    fun findById() {
        pedidoRepositoryImplement.save(pedido)

        val res = pedidoRepositoryImplement.findById(pedido.id)

        assert(res == pedido)
    }

    @Test
    fun findByIdNull() {
        val res = pedidoRepositoryImplement.findById(-5)

        assert(res == null)

    }

    @Test
    fun save() {
        usuarioRepositoryImplement.save(usuario)
        val res = pedidoRepositoryImplement.save(pedido)

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
        pedidoRepositoryImplement.save(pedido)

        val res = pedidoRepositoryImplement.delete(pedido)

        assert(res)
    }

    @Test
    fun deleteNull() {
        val res = pedidoRepositoryImplement.delete(pedido)

        assert(!res)
    }

}