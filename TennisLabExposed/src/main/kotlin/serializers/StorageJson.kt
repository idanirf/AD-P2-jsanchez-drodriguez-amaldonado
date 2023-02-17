package serializers

import dto.PedidoDTO
import dto.ProductoDTO
import dto.TurnoDTO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import java.io.File

private val logger = KotlinLogging.logger {}

/**
 * Storage j s o n
 *
 * @constructor Create empty Storage j s o n
 */
class StorageJSON {

    /**
     * Turno j s o n: Este método sirve para pasar los datos de turno en un fichero json y se almacenará en resources.
     *
     * @param nombreArchivo
     * @param turnoDTO
     */
    fun turnoJSON(nombreArchivo: String, turnoDTO: List<TurnoDTO>) {
        logger.info("Write Json turno.")
        val directorio = System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "main" +
                File.separator + "resources"
        val fichero = File(directorio + File.separator + "$nombreArchivo.json")
        val json = Json { prettyPrint = true }
        fichero.writeText(json.encodeToString(turnoDTO))
    }

    /**
     * Pedido j s o n: Este método sirve para pasar los datos de pedido en un fichero json y se almacenará en resources.
     *
     * @param nombreArchivo
     * @param pedidoDTO
     */
    fun pedidoJSON(nombreArchivo: String, pedidoDTO: List<PedidoDTO>) {
        logger.info("Write Json Pedido.")
        val directorio = System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "main" +
                File.separator + "resources"
        val fichero = File(directorio + File.separator + "$nombreArchivo.json")
        val json = Json { prettyPrint = true }
        fichero.writeText(json.encodeToString(pedidoDTO))
    }

    /**
     * Producto json: Este método sirve para pasar los datos de producto en un fichero json y se almacenará en resources.
     *
     * @param nombreArchivo
     * @param productoDTO
     */
    fun productoJson(nombreArchivo: String, productoDTO: List<ProductoDTO>) {
        logger.info("Write Json Producto.")
        val directorio = System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "main" +
                File.separator + "resources"
        val fichero = File(directorio + File.separator + "$nombreArchivo.json")
        val json = Json { prettyPrint = true }
        fichero.writeText(json.encodeToString(productoDTO))
    }
}