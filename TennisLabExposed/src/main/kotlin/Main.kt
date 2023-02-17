import config.AppConfig
import controller.*
import db.*
import dto.toDTO
import models.enums.TipoEstado
import serializers.StorageJSON

fun main() {
    println("TennisLab")
    initDataBase()
    val maquinasControllers = MaquinasController()
    val pedidosControllers = PedidosController()
    val productosController = ProductosController()
    val tareasController = TareasController()
    val turnosController = TurnosController()
    val usuariosController = UsuariosController()
    val serviceJSON = StorageJSON()

    getUsuariosInit().forEach { usuario -> usuariosController.save(usuario) }
    getTurnoInit().forEach { turno -> turnosController.save(turno) }
    getPedidosInit().forEach { pedido -> pedidosControllers.save(pedido) }
    getProductosInit().forEach { producto -> productosController.save(producto) }
    getTareasEncoradadoInit().forEach { tarea -> tareasController.saveTareaEncordado(tarea) }
    getTareasPersonalizacion().forEach { tarea -> tareasController.saveTareaPersonalizacion(tarea) }
    getMaquinasEncordar().forEach { maquina -> maquinasControllers.saveMaquinaEncordar(maquina) }
    getMaquinasPersonalizar().forEach { maquina -> maquinasControllers.saveMaquinaPersonalizar(maquina) }
    val listAsignacion = turnosController
        .findAll()
        .sortedBy { it.fechaInicio }
        .map { it.toDTO() }
    serviceJSON.turnoJSON("UserAsignaciónByDate", listAsignacion)
    val pedido = pedidosControllers.findByID(2).toDTO()
    serviceJSON.pedidoJSON("AllPedidoData", listOf(pedido))
    val allProductos = productosController
        .findAll()
        .map { it.toDTO() }
    serviceJSON.productoJson("productos", allProductos)
    val pedidosSinFinalizar = pedidosControllers
        .findAll()
        .filter { it.estado == TipoEstado.EN_PROCESO }
        .map { it.toDTO() }
    serviceJSON.pedidoJSON("pedidosSinFinalizar", pedidosSinFinalizar)

    val pedidosFinalizados = pedidosControllers
        .findAll()
        .stream()
        .filter { it.estado == TipoEstado.TERMINADO }
        .map { it.toDTO() }.toList()
    serviceJSON.pedidoJSON("pedidosFinalizados", pedidosFinalizados)





}
fun initDataBase() {
    val appConfig = AppConfig.fromPropertiesFile("src/main/resources/config.properties")
    println("Configuración: $appConfig")

    DataBaseManager.init(appConfig)

}