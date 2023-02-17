package repositories.usuario

import models.Usuario
import repositories.ICRUDRepository

interface IUsuarioRepository: ICRUDRepository<Usuario, Int> {
}