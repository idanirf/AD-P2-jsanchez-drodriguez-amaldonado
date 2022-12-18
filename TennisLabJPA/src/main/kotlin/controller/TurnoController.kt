package controller

import models.Turno
import repositories.turno.ITurnoRepository
import repositories.turno.TurnoRepositoryImplement

class TurnoController(
    private val turnoRepository: ITurnoRepository = TurnoRepositoryImplement()
) {
    fun findAll(): List<Turno> {
        return turnoRepository.findAll()
    }

    fun findById(id: Int): Turno? {
        return turnoRepository.findById(id)
    }

    fun save(turno: Turno): Turno {
        return turnoRepository.save(turno)
    }

    fun delete(turno: Turno): Boolean {
        return turnoRepository.delete(turno)
    }
}
