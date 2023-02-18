import controller.*
import db.*
import dto.toDTO
import models.enums.TipoEstado
import mu.KotlinLogging
import serializers.JSON
import utils.ApplicationProperties
import utils.logger

val logger = KotlinLogging.logger {}
fun main(){
    initDatabase()
    val maquinaController=MaquinaController()
    val pedidoController=PedidoController()
    val productoController=ProductoController()
    val tareaController=TareaController()
    val turnoController=TurnoController()
    val usuarioController=UsuarioController()
    val serviceJSON= JSON()

    getUsuariosInit().forEach{usuario -> usuarioController.save(usuario)  }
    getTurnoInit().forEach { turno ->turnoController.save(turno)  }
    getPedidosInit().forEach{pedido ->pedidoController.save(pedido)  }
    getProductosInit().forEach{producto ->productoController.save(producto)  }
    getTareasEncoradadoInit().forEach{tarea ->tareaController.saveTareaEncordado(tarea)}
    getTareasPersonalizacion().forEach { tarea ->tareaController.saveTareaPersonalizacion(tarea) }
    getMaquinasEncordar().forEach { maquina -> maquinaController.saveMaquinaEncordar(maquina) }
    getMaquinasPersonalizar().forEach { maquina -> maquinaController.saveMaquinaPersonalizar(maquina) }
//Información completa JSON pedido
    val pedido = pedidoController.findById(2)?.toDTO()
    serviceJSON.pedidoInfoJSON(listOf(pedido!!))
//Listado pedidos pendientes JSON
    val pedidosPendientes = pedidoController
        .findAll()
        .filter { it.estado == TipoEstado.EN_PROCESO }
        .map { it.toDTO() }
    serviceJSON.pedidoPendienteJSON(pedidosPendientes)
    //Pedidos completados JSON
    val pedidosCompletados = pedidoController
        .findAll()
        .stream()
        .filter { it.estado == TipoEstado.TERMINADO }
        .map { it.toDTO() }.toList()
    serviceJSON.pedidoJSONCompletados(pedidosCompletados)

//Listado de productos y servicios JSON
    val productos = productoController
        .findAll()
        .map { it.toDTO() }
    serviceJSON.productoJSON( productos)
//Listado de asignaciones para los encordadores por fecha en JSON
    val asignacion = turnoController
        .findAll()
        .sortedBy { it.fechaInicio }
        .map { it.toDTO() }
    serviceJSON.turnoJSON(asignacion)
}

fun initDatabase() {
    val properties = ApplicationProperties()
    logger.debug { "Leyendo fichero de configuración..." + properties.readProperty("app.title") }
    HibernateManager.open()
    HibernateManager.close()
}
