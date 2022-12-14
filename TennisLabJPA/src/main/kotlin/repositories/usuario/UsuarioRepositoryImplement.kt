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
        TODO("Not yet implemented")
    }

    override fun save(entity: Usuario): Usuario {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}