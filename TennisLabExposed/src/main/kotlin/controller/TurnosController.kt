package controller

import entitities.TurnoDAO
import entitities.UsuariosDAO
import models.Turno
import repositories.turno.TurnoRepositoryImplement

/**
 * Turnos controller
 *
 * @property turnoRepositoryImplement
 * @constructor Create empty Turnos controller
 */
class TurnosController (
    private val turnoRepositoryImplement: TurnoRepositoryImplement = TurnoRepositoryImplement(TurnoDAO, UsuariosDAO)
) {

    /**
     * Find all
     *
     * @return
     */
    fun findAll(): List<Turno> {
        return turnoRepositoryImplement.findAll()
    }

    /**
     * Find by id
     *
     * @param id
     * @return
     */
    fun findById(id: Int): Turno {
        return turnoRepositoryImplement.findById(id)
    }

    /**
     * Save
     *
     * @param turno
     * @return
     */
    fun save(turno: Turno): Turno {
        return turnoRepositoryImplement.save(turno)
    }

    /**
     * Delete
     *
     * @param turno
     * @return
     */
    fun delete(turno: Turno): Boolean {
        return turnoRepositoryImplement.delete(turno)
    }
}