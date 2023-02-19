package controller

import models.TareaEncordado
import models.TareaPersonalizacion
import repositories.tareas.ITareaEncordadoRepository
import repositories.tareas.ITareaPersonalizacionRepository
import repositories.tareas.TareaEncordadoRepositoryImplement
import repositories.tareas.TareaPersonalizacionRepositoryImplement

class TareaController(
    private val tareaEncordadoRepository: ITareaEncordadoRepository = TareaEncordadoRepositoryImplement(),
    private val tareaPersonalizacionRepository: ITareaPersonalizacionRepository = TareaPersonalizacionRepositoryImplement()
) {
    /**
     * Find all tarea personalizacion
     *
     * @return Devuelve todas las tareas de personalizacion.
     */
    fun findAllTareaPersonalizacion(): List<TareaPersonalizacion> {
        return tareaPersonalizacionRepository.findAll()
    }

    /**
     * Find by id tarea personalizacion
     *
     * @param id
     * @return Devuelve la tarea de personalizacion por su id.
     */
    fun findByIdTareaPersonalizacion(id: Int): TareaPersonalizacion? {
        return tareaPersonalizacionRepository.findById(id)
    }

    /**
     * Save tarea personalizacion
     *
     * @param tarea
     * @return Devuelve la tarea guardada.
     */
    fun saveTareaPersonalizacion(tarea: TareaPersonalizacion): TareaPersonalizacion {
        return tareaPersonalizacionRepository.save(tarea)
    }

    /**
     * Delete tarea personalizacion
     *
     * @param tarea
     * @return True si se ha borrado, false en caso negativo.
     */
    fun deleteTareaPersonalizacion(tarea: TareaPersonalizacion): Boolean {
        return tareaPersonalizacionRepository.delete(tarea)
    }

    /**
     * Find all tarea encordado
     *
     * @return Todas las tareas de encordado.
     */
    fun findAllTareaEncordado(): List<TareaEncordado> {
        return tareaEncordadoRepository.findAll()
    }

    /**
     * Find by id tarea encordado
     *
     * @param id
     * @return  La tarea Encordado por su id.
     */
    fun findByIdTareaEncordado(id: Int): TareaEncordado? {
        return tareaEncordadoRepository.findById(id)
    }

    /**
     * Save tarea encordado
     *
     * @param tarea
     * @return La tarea insertada.
     */
    fun saveTareaEncordado(tarea: TareaEncordado): TareaEncordado {
        return tareaEncordadoRepository.save(tarea)
    }

    /**
     * Delete tarea encordado
     *
     * @param tarea
     * @return True si se ha borrado, false en caso negativo.
     */
    fun deleteTareaEncordado(tarea: TareaEncordado): Boolean {
        return tareaEncordadoRepository.delete(tarea)
    }
}
