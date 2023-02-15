package controller

import models.Usuario
import repositories.usuario.IUsuarioRepository
import repositories.usuario.UsuarioRepositoryImplement

class UsuarioController(
    private val usuarioRepository: IUsuarioRepository = UsuarioRepositoryImplement()
) {
    fun findAll(): List<Usuario> {
        return usuarioRepository.findAll()
    }

    fun findById(id: Int): Usuario? {
        return usuarioRepository.findById(id)
    }

    fun save(usuario: Usuario): Usuario {
        return usuarioRepository.save(usuario)
    }

    fun delete(usuario: Usuario): Boolean {
        return usuarioRepository.delete(usuario)
    }
}