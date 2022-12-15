package repositories.turno

import models.Turno
import repositories.ICRUDRepository

interface ITurnoRepository : ICRUDRepository<Turno, Int> {
}