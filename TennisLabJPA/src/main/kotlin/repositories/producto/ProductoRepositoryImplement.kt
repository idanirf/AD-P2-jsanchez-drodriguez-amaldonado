package repositories.producto

import db.HibernateManager
import db.HibernateManager.manager
import models.Producto
import javax.persistence.TypedQuery

class ProductoRepositoryImplement: IProductoRepository {
    override fun findAll(): List<Producto> {
        var productosList = mutableListOf<Producto>()
        HibernateManager.query {
            val query: TypedQuery<Producto> = manager.createNamedQuery("Producto.findAll", Producto::class.java)
            productosList = query.resultList
        }
        return productosList
    }

    override fun findById(id: Int): Producto? {
        var producto: Producto? = null
        HibernateManager.query {
            producto = manager.find(Producto::class.java, id)
        }
        return producto
    }

    override fun save(entity: Producto): Producto {
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(id: Int): Boolean {
        var res = false
        HibernateManager.transaction {
            val producto = findById(id)
            if (producto != null) {
                manager.remove(producto)
                res = true
            }
        }
        return res
    }
}