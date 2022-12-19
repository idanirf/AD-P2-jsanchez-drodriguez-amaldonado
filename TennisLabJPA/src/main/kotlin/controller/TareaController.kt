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
