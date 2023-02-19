package controller

import models.Usuario
import repositories.usuario.IUsuarioRepository
import repositories.usuario.UsuarioRepositoryImplement

class UsuarioController(
    private val usuarioRepository: IUsuarioRepository = UsuarioRepositoryImplement()
) {
    /**
     * Find all: Encuentra todos los usuarios.
     *
     * @return La lista de usuarios.
     */
    fun findAll(): List<Usuario> {
        return usuarioRepository.findAll()
    }

    /**
     * Find by id: Encuentra un usuario por su id.
     *
     * @param id
     * @return Devuelve el usuario por su id.
     */
    fun findById(id: Int): Usuario? {
        return usuarioRepository.findById(id)
    }

    /**
     * Save: Guarda todos los usuarios
     *
     * @param usuario
     * @return El usuario guardado.
     */
    fun save(usuario: Usuario): Usuario {
        return usuarioRepository.save(usuario)
    }

    /**
     * Delete
     *
     * @param usuario
     * @return true si el usuario se ha borrado, false en caso negativo.
     */
    fun delete(usuario: Usuario): Boolean {
        return usuarioRepository.delete(usuario)
    }
}