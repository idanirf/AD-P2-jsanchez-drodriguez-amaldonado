package controller

import models.TareaEncordado
import models.TareaPersonalizacion
import repositories.tareas.ITareaEncordadoRepository
import repositories.tareas.ITareaPersonalizacionRepository
import repositories.tareas.TareaEncordadoRepository
import repositories.tareas.TareaPersonalizacionRepository

class TareaController(
    private val tareaEncordadoRepository: ITareaEncordadoRepository = TareaEncordadoRepository(),
    private val tareaPersonalizacionRepository: ITareaPersonalizacionRepository = TareaPersonalizacionRepository()
) {
    fun findAllTareaPersonalizacion(): List<TareaPersonalizacion> {
        return tareaPersonalizacionRepository.findAll()
    }

    fun findByIdTareaPersonalizacion(id: Int): TareaPersonalizacion? {
        return tareaPersonalizacionRepository.findById(id)
    }

    fun saveTareaPersonalizacion(tarea: TareaPersonalizacion): TareaPersonalizacion {
        return tareaPersonalizacionRepository.save(tarea)
    }

    fun deleteTareaPersonalizacion(tarea: TareaPersonalizacion): Boolean {
        return tareaPersonalizacionRepository.delete(tarea)
    }

    fun findAllTareaEncordado(): List<TareaEncordado> {
        return tareaEncordadoRepository.findAll()
    }

    fun findByIdTareaEncordado(id: Int): TareaEncordado? {
        return tareaEncordadoRepository.findById(id)
    }

    fun saveTareaEncordado(tarea: TareaEncordado): TareaEncordado {
        return tareaEncordadoRepository.save(tarea)
    }

    fun deleteTareaEncordado(tarea: TareaEncordado): Boolean {
        return tareaEncordadoRepository.delete(tarea)
    }
}
