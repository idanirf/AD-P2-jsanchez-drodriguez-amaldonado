package mapper

import entitities.TareasEncordadoDAO
import entitities.TareasPersonalizacionDAO
import models.TareaEncordado
import models.TareaPersonalizacion
import models.enums.NumeroNudos

/**
 * From tareas encordado d a o to tareas encordado
 *
 * @return El objeto ya en modelo.
 */
fun TareasEncordadoDAO.fromTareasEncordadoDAOToTareasEncordado()
        : TareaEncordado {
    return TareaEncordado(
        id = id.value,
        uuid = uuid,
        precio = precio,
        tensionVertical = tensionVertical,
        cordajeVertical = cordajeVertical,
        tensionHorizontal = tensionHorizontal,
        cordajeHorizontal = cordajeHorizontal,
        nudos = NumeroNudos.from(nudos),
        pedido = pedido.fromPedidosDAOToPedidos(),

    )
}

/**
 * From tareas personalizacion d a o to tareas personalizacion
 *
 * @return El objeto ya en modelo.
 */
fun TareasPersonalizacionDAO.fromTareasPersonalizacionDAOToTareasPersonalizacion()
        : TareaPersonalizacion {
    return TareaPersonalizacion(
        id = id.value,
        uuid = uuid,
        rigidez = rigidez,
        peso = peso,
        balance = balance,
        precio = precio,
        pedido = pedido.fromPedidosDAOToPedidos()
    )
}