package controller

import entitities.UsuariosDAO
import models.Usuario
import repositories.usuario.UsuarioRepositoryImplement

/**
 * Usuarios controller
 *
 * @property usuarioRepositoryImplement
 * @constructor Create empty Usuarios controller
 */
class UsuariosController (
    private val usuarioRepositoryImplement: UsuarioRepositoryImplement = UsuarioRepositoryImplement(UsuariosDAO)
) {

    /**
     * Find all
     *
     * @return
     */
    fun findAll(): List<Usuario> {
        return usuarioRepositoryImplement.findAll()
    }

    /**
     * Find by id
     *
     * @param id
     * @return
     */
    fun findById(id: Int): Usuario {
        return usuarioRepositoryImplement.findById(id)
    }

    /**
     * Save
     *
     * @param usuario
     * @return
     */
    fun save(usuario: Usuario): Usuario {
        return usuarioRepositoryImplement.save(usuario)
    }

    /**
     * Delete
     *
     * @param usuario
     * @return
     */
    fun delete(usuario: Usuario): Boolean {
        return usuarioRepositoryImplement.delete(usuario)
    }
}