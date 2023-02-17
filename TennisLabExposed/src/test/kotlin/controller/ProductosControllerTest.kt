package controller

import exception.ProductoException
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
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import repositories.producto.ProductoRepositoryImplement
import java.time.LocalDate
import java.util.*

@ExtendWith(MockKExtension::class)
class ProductosControllerTest {

    @MockK
    lateinit var productosRepository: ProductoRepositoryImplement

    @InjectMockKs
    lateinit var productosController: ProductosController

    private val usuario =
        Usuario(
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
        fechaEntrada = LocalDate.of(2022, 3, 15),
        fechaSalidaProgramada = LocalDate.of(2022, 5, 10),
        fechaEntrega = LocalDate.of(2022, 6, 7),
        precio = 10.20,
        usuario = usuario
    )
    private val producto = Producto(
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
    fun findAllProductos() {
        every { productosRepository.findAll() } returns listOf(producto)
        val res = productosController.findAll()
        assert(res == listOf(producto))
        verify(exactly = 1) { productosRepository.findAll() }
    }

    @Test
    fun findByIdProductoById() {
        every { productosRepository.findById(producto.id) } returns producto
        val res = productosController.findByID(producto.id)
        assert(res == producto)
        verify(exactly = 1) { productosRepository.findById(producto.id) }
    }

    @Test
    fun findByIdNoExisteProducto() {
        every { productosRepository.findById(producto.id) } throws ProductoException("Error: No encontrado Producto con id: ${producto.id}")
        val res = assertThrows<ProductoException> {
            productosController.findByID(producto.id)
        }

        assert(res.message == "Error: No encontrado Producto con id: ${producto.id}")
        verify(exactly = 1) { productosRepository.findById(producto.id) }
    }

    @Test
    fun saveProducto() {
        every { productosRepository.save(producto) } returns producto
        val res = productosController.save(producto)
        assert(res == producto)
        verify(exactly = 1) { productosRepository.save(producto) }
    }

    @Test
    fun deleteProducto() {
        every { productosRepository.delete(producto) } returns true
        val res = productosController.delete(producto)
        assert(res)
        verify(exactly = 1) { productosRepository.delete(producto) }
    }

    @Test
    fun deleteProductoNoExiste() {
        every { productosRepository.delete(producto) } throws ProductoException("Error: No encontrado Producto con id: ${producto.id}")
        val res = assertThrows<ProductoException> {
            productosController.delete(producto)
        }
        assert(res.message == "Error: No encontrado Producto con id: ${producto.id}")
        verify(exactly = 1) { productosRepository.delete(producto) }
    }
}