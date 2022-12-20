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
    println("\uD83C\uDFBE TennisLab \uD83C\uDFBE")
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

    /*val pedido = pedidoController.findById(2)?.toDTO()
    serviceJSON.pedidoJSON("", listOf(pedido!!))

    val pedidosPendientes = pedidoController
        .findAll()
        .filter { it.estado == TipoEstado.EN_PROCESO }
        .map { it.toDTO() }
    serviceJSON.pedidoJSON("PedidosPendientes", pedidosPendientes)

    val productos = productoController
        .findAll()
        .map { it.toDTO() }
    serviceJSON.productoJSON("productos", productos)

    val asignacion = turnoController
        .findAll()
        .sortedBy { it.fechaInicio }
        .map { it.toDTO() }
    serviceJSON.turnoJSON("", asignacion)*/

}


fun initDatabase() {
    val properties = ApplicationProperties()
    logger.debug { "Leyendo fichero de configuración..." + properties.readProperty("app.title") }
    HibernateManager.open()
    HibernateManager.close()
}
