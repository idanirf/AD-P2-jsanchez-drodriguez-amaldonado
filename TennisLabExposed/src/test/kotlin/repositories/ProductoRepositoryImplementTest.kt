package repositories

import config.AppConfig
import db.DataBaseManager
import entitities.PedidosDAO
import entitities.ProductosDAO
import entitities.UsuariosDAO
import exception.ProductoException
import models.Pedido
import models.Producto
import models.Usuario
import models.enums.TipoEstado
import models.enums.TipoProducto
import models.enums.TipoUsuario
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import repositories.pedido.PedidoRepositoryImplement
import repositories.producto.ProductoRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDate
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductoRepositoryImplementTest {
    private val productosRepositoryImplement = ProductoRepositoryImplement(ProductosDAO, PedidosDAO)
    private val pedidosRepositoryImplement = PedidoRepositoryImplement(PedidosDAO, UsuariosDAO)
    private val usuariosRepositoryImplement = UsuarioRepositoryImplement(UsuariosDAO)
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
            precio = pedido.precio
            usuario = usuarioDAO
        }

        ProductosDAO.new(producto.id) {
            uuid = producto.uuid
            tipoProducto = producto.tipoProducto
            marca = producto.marca
            modelo = producto.modelo
            precio = producto.precio
            stock = producto.stock
            pedido = pedidoDAO
        }
    }

    @Test
    fun findAll() {
        val res = productosRepositoryImplement.findAll()
        assert(res.isEmpty())
    }

    @Test
    fun findById() = transaction {
        saveData()
        val res = productosRepositoryImplement.findById(producto.id)
        assert(res == producto)
    }

    @Test
    fun findByIdNoExiste() {
        assertThrows<ProductoException> {
            val res = productosRepositoryImplement.findById(-5)
        }

    }

    @Test
    fun saveInsert() {
        usuariosRepositoryImplement.save(usuario)
        pedidosRepositoryImplement.save(pedido)
        val res = productosRepositoryImplement.save(producto)
        assertAll(
            { Assertions.assertEquals(res.id, producto.id) },
            { Assertions.assertEquals(res.uuid, producto.uuid) },
            { Assertions.assertEquals(res.tipoProducto, producto.tipoProducto) },
            { Assertions.assertEquals(res.marca, producto.marca) },
            { Assertions.assertEquals(res.modelo, producto.modelo) },
            { Assertions.assertEquals(res.precio, producto.precio) },
            { Assertions.assertEquals(res.stock, producto.stock) }
        )
    }

    @Test
    fun saveUpdate() = transaction {
        saveData()
        val res = productosRepositoryImplement.save(producto)
        assert(res == producto)
    }

    @Test
    fun delete() = transaction {
        saveData()
        val res = productosRepositoryImplement.delete(producto)
        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = productosRepositoryImplement.delete(producto)
        assert(!res)
    }
}