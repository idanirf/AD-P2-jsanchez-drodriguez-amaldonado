/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package repositories.usuario

import db.HibernateManager
import db.HibernateManager.manager
import models.Usuario
import javax.persistence.TypedQuery

class UsuarioRepositoryImplement: IUsuarioRepository {
    /**
     * Find all
     *
     * @return Devuelve todos los usuarios.
     */
    override fun findAll(): List<Usuario> {
        var usuarios = mutableListOf<Usuario>()
        HibernateManager.query {
            val query: TypedQuery<Usuario> = manager.createNamedQuery("Usuario.findAll", Usuario::class.java)
            usuarios = query.resultList
        }
        return usuarios

    }

    /**
     * Find by id
     *
     * @param id
     * @return Devuelve un usuario.
     */
    override fun findById(id: Int): Usuario? {
        var usuario: Usuario? = null
        HibernateManager.query {
            usuario = manager.find(Usuario::class.java, id)
        }
        return usuario
    }

    /**
     * Save
     *
     * @param entity
     * @return Devuelve el usuario insetado
     */
    override fun save(entity: Usuario): Usuario {
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    /**
     * Delete
     *
     * @param entity
     * @return Devuelve el usuario borrado
     */
    override fun delete(entity: Usuario): Boolean {
        var res = false
        HibernateManager.transaction {
            val usuario = manager.find(Usuario::class.java, entity.id)
            usuario?.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }
}