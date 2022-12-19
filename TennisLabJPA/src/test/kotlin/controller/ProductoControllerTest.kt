package controller

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Pedido
import models.Producto
import models.Usuario
import models.enums.TipoEstado
import models.enums.TipoProducto
import models.enums.TipoUsuario
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import repositories.producto.ProductoRepositoryImplement
import java.time.LocalDate
import java.util.*

@ExtendWith(MockKExtension::class)
internal class ProductoControllerTest {
    @MockK
    lateinit var productoRepositoryImplement: ProductoRepositoryImplement
    @InjectMockKs
    lateinit var productoController: ProductoController
    private val usuario=
        Usuario(
            id = 3,
            uuid = UUID.randomUUID(),
            nombre = "Cristiano",
            apellido = "Ronaldo",
            correo = "cristiano@ronaldo.com",
            password = "1234",
            tipoUsuario = TipoUsuario.USUARIO
        )
    private val pedido= Pedido(
        id = 0,
        uuid = UUID.randomUUID(),
        estado = TipoEstado.TERMINADO,
        fechaEntrada = LocalDate.of(2022, 3, 15),
        fechaSalidaProgramada = LocalDate.of(2022, 5, 10),
        fechaEntrega = LocalDate.of(2022, 6, 7),
        precio = 10.20,
        usuario = usuario
    )
    private val producto= Producto(
        id = 4,
        uuid = UUID.randomUUID(),
        marca = "Nike",
        modelo = "H10",
        precio = 50.45,
        stock = 120,
        tipoProducto = TipoProducto.CORDAJE,
        pedido = pedido
    )
    init {
        MockKAnnotations.init(this)
    }
    @Test
    fun findAllProductos(){
        every { productoRepositoryImplement.findAll() }returns listOf(producto)
        val res= productoController.findAll()
        assert(res == listOf(producto))
        verify(exactly = 1) {productoRepositoryImplement.findAll()  }
    }
    @Test
    fun findByIdProducto(){
        every{productoRepositoryImplement.findById(producto.id)} returns producto
        val res = productoController.findById(producto.id)
        assert(res==producto)
        verify(exactly = 1) {productoRepositoryImplement.findById(producto.id)  }

    }
    @Test
    fun findByIdNoExisteProducto(){
        every { productoRepositoryImplement.findById(producto.id) }returns null
        val res = productoController.findById(producto.id)
        assert(res == null)
        verify (exactly = 1){productoRepositoryImplement.findById(producto.id)}
    }
    @Test
    fun saveProducto(){
        every{productoRepositoryImplement.save(producto)}returns producto
        val res = productoController.save(producto)
        assert(res == producto)
        verify (exactly = 1){productoRepositoryImplement.save(producto)}
    }
    @Test
    fun deleteProducto(){
        every { productoRepositoryImplement.delete(producto) }returns true
        val res = productoController.delete(producto)
        assert(res)
        verify (exactly = 1){productoRepositoryImplement.delete(producto)}
    }
    @Test
    fun deleteProductoNoExiste(){
        every { productoRepositoryImplement.delete(producto) }returns false
        val res = productoController.delete(producto)
        assert(!res)
        verify (exactly = 1){productoRepositoryImplement.delete(producto)}
    }
}