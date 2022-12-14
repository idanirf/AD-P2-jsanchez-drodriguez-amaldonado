package repositories.usuario

import models.Usuario
import repositories.ICRUDRepository

interface IUsuarioRepository: ICRUDRepository<Usuario, Int> {
    // Implementamos los métodos que nos vayan haciendo falta.
}