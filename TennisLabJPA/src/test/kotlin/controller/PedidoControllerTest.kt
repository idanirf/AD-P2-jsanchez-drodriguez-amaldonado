package controller

import db.getUsuariosInit
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Pedido
import models.Usuario
import models.enums.TipoEstado
import models.enums.TipoUsuario
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import repositories.pedido.PedidoRepositoryImplement
import java.time.LocalDate
import java.util.*

@ExtendWith(MockKExtension::class)
class PedidoControllerTest {
    @MockK
    lateinit var pedidoRepositoryImplement: PedidoRepositoryImplement
    @InjectMockKs
    lateinit var pedidoController: PedidoController

    private val usuario = Usuario(
        id = 3,
        uuid = UUID.randomUUID(),
        nombre = "Cristiano",
        apellido = "Ronaldo",
        correo = "cristiano@ronaldo.com",
        password = "1234",
        tipoUsuario = TipoUsuario.USUARIO
    )
    private val pedido = Pedido(
        id = 1,
        uuid = UUID.randomUUID(),
        estado = TipoEstado.TERMINADO,
        fechaEntrada = LocalDate.of(2022, 2, 15),
        fechaSalidaProgramada = LocalDate.of(2022, 6, 10),
        fechaEntrega = LocalDate.of(2022, 7, 10),
        precio = 10.20,
        usuario = getUsuariosInit()[1]
    )
    init {
        MockKAnnotations.init(this)
    }
    @Test
    fun findAllPedido(){
        every { pedidoRepositoryImplement.findAll() } returns listOf(pedido)
        val res = pedidoController.findAll()
        assert(res == listOf(pedido))
        verify (exactly = 1){pedidoRepositoryImplement.findAll()}
    }
    @Test
    fun findByIdPedido(){
        every{pedidoRepositoryImplement.findById(pedido.id)} returns pedido
        val res = pedidoController.findById(pedido.id)
        assert(res == pedido)
        verify (exactly = 1) {pedidoRepositoryImplement.findById(pedido.id)}
    }
    @Test
    fun findByIdPedidoNoExiste(){
        every { pedidoRepositoryImplement.findById(pedido.id) }returns null
        val res = pedidoController.findById(pedido.id)
        assert(res==null)
        verify (exactly = 1){pedidoRepositoryImplement.findById(pedido.id)}
    }
    @Test
    fun savePedido(){
        every{pedidoRepositoryImplement.save(pedido)}returns  pedido
        val res = pedidoController.save(pedido)
        assert(res == pedido)
        verify(exactly = 1){pedidoRepositoryImplement.save(pedido)}
    }
    @Test
    fun deletePedido(){
        every{pedidoRepositoryImplement.delete(pedido)}returns true
        val res = pedidoController.delete(pedido)
        assert(res)
        verify (exactly = 1){pedidoRepositoryImplement.delete(pedido)}
    }
    @Test
    fun deleteNoExistePedido(){
        every { pedidoRepositoryImplement.delete(pedido) } returns false
        val res = pedidoController.delete(pedido)
        assert(!res)

        verify (exactly = 1){pedidoRepositoryImplement.delete(pedido)}
    }
}