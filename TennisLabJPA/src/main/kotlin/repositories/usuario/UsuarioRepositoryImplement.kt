package repositories.usuario

import antlr.preprocessor.Hierarchy
import db.HibernateManager
import db.HibernateManager.manager
import models.Usuario
import javax.persistence.TypedQuery

class UsuarioRepositoryImplement: IUsuarioRepository {
    override fun findAll(): List<Usuario> {
        var usuarios = mutableListOf<Usuario>()
        HibernateManager.query {
            val query: TypedQuery<Usuario> = manager.createNamedQuery("Usuario.findAll", Usuario::class.java)
            usuarios = query.resultList
        }
        return usuarios

    }

    override fun findById(id: Int): Usuario? {
        var usuario: Usuario? = null
        HibernateManager.query {
            usuario = manager.find(Usuario::class.java, id)
        }
        return usuario
    }

    override fun save(entity: Usuario): Usuario {
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(id: Int): Boolean {
        var res = false
        HibernateManager.transaction {
            val usuario = findById(id)
            if (usuario != null) {
                manager.remove(usuario)
                res = true
            }
        }
        return res
    }
}