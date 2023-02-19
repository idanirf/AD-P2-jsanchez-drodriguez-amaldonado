package controller

import models.Turno
import repositories.turno.ITurnoRepository
import repositories.turno.TurnoRepositoryImplement

class TurnoController(
    private val turnoRepository: ITurnoRepository = TurnoRepositoryImplement()
) {
    /**
     * Find all
     *
     * @return Devuelve todos los turnos.
     */
    fun findAll(): List<Turno> {
        return turnoRepository.findAll()
    }

    /**
     * Find by id
     *
     * @param id
     * @return Devuelve el turno por su id.
     */
    fun findById(id: Int): Turno? {
        return turnoRepository.findById(id)
    }

    /**
     * Save
     *
     * @param turno
     * @return Devuelve el turno insertado.
     */
    fun save(turno: Turno): Turno {
        return turnoRepository.save(turno)
    }

    /**
     * Delete
     *
     * @param turno
     * @return Devuelve el turno borrado.
     */
    fun delete(turno: Turno): Boolean {
        return turnoRepository.delete(turno)
    }
}
