/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package repositories.producto

import db.HibernateManager
import db.HibernateManager.manager
import models.Producto
import javax.persistence.TypedQuery

class ProductoRepositoryImplement: IProductoRepository {
    /**
     * Find all
     *
     * @return Devuelve todas los productos..
     */
    override fun findAll(): List<Producto> {
        var productosList = mutableListOf<Producto>()
        HibernateManager.query {
            val query: TypedQuery<Producto> = manager.createNamedQuery("Producto.findAll", Producto::class.java)
            productosList = query.resultList
        }
        return productosList
    }

    /**
     * Find by id
     *
     * @param id
     * @return Devuelve el producto por su id.
     */
    override fun findById(id: Int): Producto? {
        var producto: Producto? = null
        HibernateManager.query {
            producto = manager.find(Producto::class.java, id)
        }
        return producto
    }

    /**
     * Save
     *
     * @param entity
     * @return Devuelve el producto insertada.
     */
    override fun save(entity: Producto): Producto {
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    /**
     * Delete
     *
     * @param entity
     * @return Devuelve true si se ha borrado el producto o false en caso negativo.
     */
    override fun delete(entity: Producto): Boolean {
        var res = false
        HibernateManager.transaction {
            val producto = manager.find(Producto::class.java, entity.id)
            producto?.let {
                manager.remove(it)
                res= true
            }
        }
        return res
    }
}