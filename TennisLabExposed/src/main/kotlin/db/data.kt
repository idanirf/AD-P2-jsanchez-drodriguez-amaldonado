package db

import models.*
import models.enums.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * Get usuarios init
 *
 */
fun getUsuariosInit() = listOf(
    Usuario(
        id = 0,
        uuid = UUID.randomUUID(),
        nombre = "Alfonso",
        apellido = "Cabello",
        correo = "alfonso@cabello.com",
        password = "1234",
        tipoUsuario = TipoUsuario.USUARIO
    ),
    Usuario(
        id = 1,
        uuid = UUID.randomUUID(),
        nombre = "Marcelo",
        apellido = "Alvarez",
        correo = "marcelo@alvarez.com",
        password = "1234",
        tipoUsuario = TipoUsuario.ADMINISTRADOR
    ),
    Usuario(
        id = 2,
        uuid = UUID.randomUUID(),
        nombre = "Fernando",
        apellido = "Alonso",
        correo = "fernado@alonso.com",
        password = "1234",
        tipoUsuario = TipoUsuario.ENCORDADOR
    ),
    Usuario(
        id = 3,
        uuid = UUID.randomUUID(),
        nombre = "Cristiano",
        apellido = "Ronaldo",
        correo = "cristiano@ronaldo.com",
        password = "1234",
        tipoUsuario = TipoUsuario.USUARIO
    ),
    Usuario(
        id = 4,
        uuid = UUID.randomUUID(),
        nombre = "Rafael",
        apellido = "Nadal",
        correo = "rafael@nadal.com",
        password = "1234",
        tipoUsuario = TipoUsuario.ADMINISTRADOR
    )
)

/**
 * Get productos init
 *
 */
fun getProductosInit() = listOf(
    Producto(
        id = 0,
        uuid = UUID.randomUUID(),
        marca = "Nike",
        modelo = "H10",
        precio = 50.45,
        stock = 120,
        tipoProducto = TipoProducto.CORDAJE,
        pedido = getPedidosInit()[0]
    ),
    Producto(
        id = 1,
        uuid = UUID.randomUUID(),
        marca = "Nike",
        modelo = "H10",
        precio = 50.45,
        stock = 120,
        tipoProducto = TipoProducto.CORDAJE,
        pedido = getPedidosInit()[1]
    ),
    Producto(
        id = 2,
        uuid = UUID.randomUUID(),
        marca = "Nike",
        modelo = "H10",
        precio = 50.45,
        stock = 120,
        tipoProducto = TipoProducto.CORDAJE,
        pedido = getPedidosInit()[2]
    ),
    Producto(
        id = 3,
        uuid = UUID.randomUUID(),
        marca = "Nike",
        modelo = "H10",
        precio = 50.45,
        stock = 120,
        tipoProducto = TipoProducto.CORDAJE,
        pedido = getPedidosInit()[3]
    ),
    Producto(
        id = 4,
        uuid = UUID.randomUUID(),
        marca = "Nike",
        modelo = "H10",
        precio = 50.45,
        stock = 120,
        tipoProducto = TipoProducto.CORDAJE,
        pedido = getPedidosInit()[4]
    ),
)

/**
 * Get pedidos init
 *
 */
fun getPedidosInit() = listOf(
    Pedido(
        id = 0,
        uuid = UUID.randomUUID(),
        estado = TipoEstado.EN_PROCESO,
        fechaEntrada = LocalDate.of(2022, 3, 15),
        fechaSalidaProgramada = LocalDate.of(2022, 5, 10),
        fechaEntrega = LocalDate.of(2022, 6, 7),
        precio = 10.20,
        usuario = getUsuariosInit()[0]
    ),
    Pedido(
        id = 1,
        uuid = UUID.randomUUID(),
        estado = TipoEstado.RECIBIDO,
        fechaEntrada = LocalDate.of(2022, 2, 15),
        fechaSalidaProgramada = LocalDate.of(2022, 6, 10),
        fechaEntrega = LocalDate.of(2022, 7, 10),
        precio = 10.20,
        usuario = getUsuariosInit()[1]
    ),

    Pedido(
        id = 2,
        uuid = UUID.randomUUID(),
        estado = TipoEstado.TERMINADO,
        fechaEntrada = LocalDate.of(2022, 4, 15),
        fechaSalidaProgramada = LocalDate.of(2022, 9, 10),
        fechaEntrega = LocalDate.of(2022, 10, 10),
        precio = 10.20,
        usuario = getUsuariosInit()[2]
    ),
    Pedido(
        id = 3,
        uuid = UUID.randomUUID(),
        estado = TipoEstado.TERMINADO,
        fechaEntrada = LocalDate.of(2022, 6, 15),
        fechaSalidaProgramada = LocalDate.of(2022, 6, 20),
        fechaEntrega = LocalDate.of(2022, 7, 10),
        precio = 10.20,
        usuario = getUsuariosInit()[3]
    ),
    Pedido(
        id = 4,
        uuid = UUID.randomUUID(),
        estado = TipoEstado.TERMINADO,
        fechaEntrada = LocalDate.of(2022, 6, 15),
        fechaSalidaProgramada = LocalDate.of(2022, 7, 10),
        fechaEntrega = LocalDate.of(2022, 8, 10),
        precio = 10.20,
        usuario = getUsuariosInit()[4]
    ),
)

/**
 * Get tareas encoradado init
 *
 */
fun getTareasEncoradadoInit() = listOf(
    TareaEncordado(
        id = 0,
        uuid = UUID.randomUUID(),
        precio = 100.50,
        tensionVertical = 22.50,
        cordajeVertical = "Babolat",
        tensionHorizontal = 26.10,
        cordajeHorizontal = "Babolat",
        nudos = NumeroNudos.DOS,
        pedido = getPedidosInit()[0],
    ),
    TareaEncordado(
        id = 1,
        uuid = UUID.randomUUID(),
        precio = 100.50,
        tensionVertical = 22.50,
        cordajeVertical = "Wilson",
        tensionHorizontal = 26.10,
        cordajeHorizontal = "Wilson",
        nudos = NumeroNudos.DOS,
        pedido = getPedidosInit()[1],
    ),
)

/**
 * Get tareas personalizacion
 *
 */
fun getTareasPersonalizacion() = listOf(
    TareaPersonalizacion(
        id = 0,
        uuid = UUID.randomUUID(),
        rigidez = 69.69,
        peso = 0.20,
        balance = 327.10,
        precio = 99.99,
        pedido = getPedidosInit()[2]
    ),
    TareaPersonalizacion(
        id = 1,
        uuid = UUID.randomUUID(),
        rigidez = 79.69,
        peso = 0.27,
        balance = 326.10,
        precio = 89.99,
        pedido = getPedidosInit()[3]
    ),
    TareaPersonalizacion(
        id = 2,
        uuid = UUID.randomUUID(),
        rigidez = 59.69,
        peso = 0.25,
        balance = 327.10,
        precio = 79.99,
        pedido = getPedidosInit()[4]
    ),
)

/**
 * Get maquinas encordar
 *
 */
fun getMaquinasEncordar() = listOf(
    MaquinaEncordar(
        id = 0,
        uuid = UUID.randomUUID(),
        marca = "HEAD",
        modelo = "2020",
        fechaAdquisicion = LocalDate.of(2022, 3, 5),
        numeroSerie = 20,
        tipo = TipoEncordaje.MANUAL,
        tensionMaxima = 25.10,
        tensionMinima = 22.60,
        turno = getTurnoInit()[0]
    ),
    MaquinaEncordar(
        id = 1,
        uuid = UUID.randomUUID(),
        marca = "HEAD",
        modelo = "2020",
        fechaAdquisicion = LocalDate.of(2022, 5, 5),
        numeroSerie = 20,
        tipo = TipoEncordaje.MANUAL,
        tensionMaxima = 26.10,
        tensionMinima = 23.60,
        turno = getTurnoInit()[0]
    ),
)

/**
 * Get maquinas personalizar
 *
 */
fun getMaquinasPersonalizar() = listOf(
    MaquinaPersonalizar(
        id = 0,
        uuid = UUID.randomUUID(),
        marca = "Signum Pro",
        modelo = "30",
        fechaAdquisicion = LocalDate.of(2022, 4, 15),
        numeroSerie = 7,
        swingweight = true,
        balance = 300.80,
        rigidez = 100.60,
        turno = getTurnoInit()[0],
    ),
    MaquinaPersonalizar(
        id = 1,
        uuid = UUID.randomUUID(),
        marca = "Solinco",
        modelo = "50",
        fechaAdquisicion = LocalDate.of(2022, 5, 15),
        numeroSerie = 9,
        swingweight = false,
        balance = 310.80,
        rigidez = 120.60,
        turno = getTurnoInit()[1],
    ),
)

/**
 * Get turno init
 *
 */
fun getTurnoInit() = listOf(
    Turno(
        id = 0,
        uuid = UUID.randomUUID(),
        fechaInicio = LocalDateTime.of(2022, 10, 14, 9, 10),
        fechaFinal = LocalDateTime.of(2022, 11, 20, 10, 20),
        usuario = getUsuariosInit()[0]
    ),
    Turno(
        id = 1,
        uuid = UUID.randomUUID(),
        fechaInicio = LocalDateTime.of(2022, 8, 14, 9, 10),
        fechaFinal = LocalDateTime.of(2022, 9, 20, 10, 20),
        usuario = getUsuariosInit()[1]
    ),
    Turno(
        id = 2,
        uuid = UUID.randomUUID(),
        fechaInicio = LocalDateTime.of(2022, 7, 14, 9, 10),
        fechaFinal = LocalDateTime.of(2022, 8, 20, 10, 20),
        usuario = getUsuariosInit()[2]
    ),
)