package controller

import entitities.PedidosDAO
import entitities.TareasEncordadoDAO
import entitities.TareasPersonalizacionDAO
import models.TareaEncordado
import models.TareaPersonalizacion
import repositories.tareas.TareaEncordadoRepositoryImplement
import repositories.tareas.TareaPersonalizacionRepositoryImplement

/**
 * Tareas controller
 *
 * @property tareaEncordadoRepository
 * @property tareaPersonalizacionRepository
 * @constructor Create empty Tareas controller
 */
class TareasController (
    private val tareaEncordadoRepository: TareaEncordadoRepositoryImplement =
        TareaEncordadoRepositoryImplement(TareasEncordadoDAO, PedidosDAO),
    private val tareaPersonalizacionRepository: TareaPersonalizacionRepositoryImplement =
        TareaPersonalizacionRepositoryImplement(TareasPersonalizacionDAO, PedidosDAO)
) {
    /**
     * Find all tarea encordado
     *
     * @return
     */
    fun findAllTareaEncordado(): List<TareaEncordado> {
        return tareaEncordadoRepository.findAll()
    }

    /**
     * Find all tarea encordado by id
     *
     * @param id
     * @return
     */
    fun findAllTareaEncordadoById(id: Int): TareaEncordado {
        return tareaEncordadoRepository.findById(id)
    }

    /**
     * Save tarea encordado
     *
     * @param tarea
     * @return
     */
    fun saveTareaEncordado(tarea: TareaEncordado): TareaEncordado {
        return tareaEncordadoRepository.save(tarea)
    }

    /**
     * Delete tarea encordado
     *
     * @param tarea
     * @return
     */
    fun deleteTareaEncordado(tarea: TareaEncordado): Boolean {
        return tareaEncordadoRepository.delete(tarea)
    }

    /**
     * Find all tarea personalizacion
     *
     * @return
     */
    fun findAllTareaPersonalizacion(): List<TareaPersonalizacion> {
        return tareaPersonalizacionRepository.findAll()
    }

    /**
     * Find all tarea personalizacion by id
     *
     * @param id
     * @return
     */
    fun findAllTareaPersonalizacionById(id: Int): TareaPersonalizacion {
        return tareaPersonalizacionRepository.findById(id)
    }

    /**
     * Save tarea personalizacion
     *
     * @param tarea
     * @return
     */
    fun saveTareaPersonalizacion(tarea: TareaPersonalizacion): TareaPersonalizacion {
        return tareaPersonalizacionRepository.save(tarea)
    }

    /**
     * Delete tarea personalizacion
     *
     * @param tarea
     * @return
     */
    fun deleteTareaPersonalizacion(tarea: TareaPersonalizacion): Boolean {
        return tareaPersonalizacionRepository.delete(tarea)
    }
}