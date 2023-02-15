package repositories

import db.HibernateManager
import models.Pedido
import models.Producto
import models.Usuario
import models.enums.TipoEstado
import models.enums.TipoProducto
import models.enums.TipoUsuario
import org.junit.jupiter.api.*
import repositories.pedido.PedidoRepositoryImplement
import repositories.producto.ProductoRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDate
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductoRepositoryImplementTest {
    private val productoRepository = ProductoRepositoryImplement()
    private val usuarioRepository = UsuarioRepositoryImplement()
    private val pedidoRepository = PedidoRepositoryImplement()

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
    private val producto = Producto(
        id = 0,
        uuid = UUID.randomUUID(),
        marca = "Nike",
        modelo = "H10",
        precio = 50.45,
        stock = 120,
        tipoProducto = TipoProducto.CORDAJE,
        pedido = pedido
    )

    @AfterEach
    fun tearDown() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM Producto")
            query.executeUpdate()
        }
    }

    @BeforeEach
    fun beforeEach() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM Producto")
            query.executeUpdate()
        }
    }

    @BeforeAll
    fun setUp() {
        usuarioRepository.save(usuario)
        pedidoRepository.save(pedido)
    }

    @Test
    fun findAll() {
        val res = productoRepository.findAll()
        assert(res.isEmpty())
    }

    @Test
    fun findById() {
        productoRepository.save(producto)
        val res = productoRepository.findById(producto.id)

        assert(res == producto)
    }

    @Test
    fun findByIdNoExiste() {
        val res = productoRepository.findById(-5)

        assert(res == null)
    }

    @Test
    fun saveInsert() {
        val res = productoRepository.save(producto)

        assertAll(
            { Assertions.assertEquals(res.id, producto.id) },
            { Assertions.assertEquals(res.uuid, producto.uuid) },
            { Assertions.assertEquals(res.marca, producto.marca) },
            { Assertions.assertEquals(res.modelo, producto.modelo) },
            { Assertions.assertEquals(res.precio, producto.precio) },
            { Assertions.assertEquals(res.stock, producto.stock) },
            { Assertions.assertEquals(res.tipoProducto, producto.tipoProducto) },
            { Assertions.assertEquals(res.pedido, producto.pedido) },
        )
    }
    @Test
    fun delete(){
        productoRepository.save(producto)
        val res = productoRepository.delete(producto)

        assert(res)
    }
    @Test
    fun deleteNoExiste(){
        val res = productoRepository.delete(producto)

        assert(!res)
    }
}