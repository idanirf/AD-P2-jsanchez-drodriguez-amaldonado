package repositories.usuario

import models.Usuario
import repositories.ICRUDRepository

/**
 * I usuario repository
 *
 * @constructor Create empty I usuario repository
 */
interface IUsuarioRepository : ICRUDRepository<Usuario, Int> {
}