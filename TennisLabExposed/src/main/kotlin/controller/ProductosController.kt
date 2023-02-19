package controller

import entitities.PedidosDAO
import entitities.ProductosDAO
import models.Producto
import repositories.producto.ProductoRepositoryImplement

/**
 * Productos controller
 *
 * @property productoRepository
 * @constructor Create empty Productos controller
 */
class ProductosController (
    private val productoRepository: ProductoRepositoryImplement = ProductoRepositoryImplement(
        ProductosDAO,
        PedidosDAO

    )
) {
    /**
     * Find all
     *
     * @return
     */
    fun findAll(): List<Producto> {
        return productoRepository.findAll()
    }

    /**
     * Find by i d
     *
     * @param id
     * @return
     */
    fun findByID(id: Int): Producto? {
        return productoRepository.findById(id)
    }

    /**
     * Save
     *
     * @param entity
     * @return
     */
    fun save(entity: Producto): Producto {
        return productoRepository.save(entity)
    }

    /**
     * Delete
     *
     * @param entity
     * @return
     */
    fun delete(entity: Producto): Boolean {
        return productoRepository.delete(entity)
    }
}