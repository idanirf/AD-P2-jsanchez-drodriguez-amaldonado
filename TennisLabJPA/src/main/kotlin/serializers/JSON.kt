/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package serializers

import dto.PedidoDTO
import dto.ProductoDTO
import dto.TurnoDTO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class JSON {

    fun turnoJSON( turnoDTO: List<TurnoDTO>) {
        val directorio = System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "main" +
                File.separator + "resources"
        val fichero = File(directorio + File.separator + "5_turnosDTO.json")
        val json = Json { prettyPrint = true }
        fichero.writeText(json.encodeToString(turnoDTO))
    }

    fun pedidoJSONCompletados(pedidoDTO: List<PedidoDTO>) {
        val directorio = System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "main" +
                File.separator + "resources"
        val fichero = File(directorio + File.separator + "3_pedidosJSON.json")
        val json = Json { prettyPrint = true }
        fichero.writeText(json.encodeToString(pedidoDTO))
    }
    fun pedidoInfoJSON(pedidoDTO: List<PedidoDTO>) {
        val directorio = System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "main" +
                File.separator + "resources"
        val fichero = File(directorio + File.separator + "1_pedidoInfoJSON.json")
        val json = Json { prettyPrint = true }
        fichero.writeText(json.encodeToString(pedidoDTO))
    }
    fun pedidoPendienteJSON(pedidoDTO: List<PedidoDTO>) {
        val directorio = System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "main" +
                File.separator + "resources"
        val fichero = File(directorio + File.separator + "2_pedidoPendientesJSON.json")
        val json = Json { prettyPrint = true }
        fichero.writeText(json.encodeToString(pedidoDTO))
    }

    fun productoJSON(productoDTO: List<ProductoDTO>) {
        val directorio = System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "main" +
                File.separator + "resources"
        val fichero = File(directorio + File.separator + "4_productosJSON.json")
        val json = Json { prettyPrint = true }
        fichero.writeText(json.encodeToString(productoDTO))
    }
}