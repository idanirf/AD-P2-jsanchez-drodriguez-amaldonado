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
    private val productoRepositoryImplement = ProductoRepositoryImplement()
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
    fun eliminar() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM Producto")
            query.executeUpdate()
        }
    }

    @BeforeEach
    fun porCadaUna() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM Producto")
            query.executeUpdate()
        }
    }

    @BeforeAll
    fun guardado() {
        usuarioRepositoryImplement.save(usuario)
        pedidoRepositoryImplement.save(pedido)
    }

    @Test
    fun findAll() {
        val res = productoRepositoryImplement.findAll()
        assert(res.isEmpty())
    }

    @Test
    fun findById() {
        productoRepositoryImplement.save(producto)
        val res = productoRepositoryImplement.findById(producto.id)

        assert(res == producto)
    }

    @Test
    fun findByIdNull() {
        val res = productoRepositoryImplement.findById(-5)

        assert(res == null)
    }

    @Test
    fun save() {
        val res = productoRepositoryImplement.save(producto)

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
        productoRepositoryImplement.save(producto)
        val res = productoRepositoryImplement.delete(producto)

        assert(res)
    }
    @Test
    fun deleteNull(){
        val res = productoRepositoryImplement.delete(producto)

        assert(!res)
    }
}