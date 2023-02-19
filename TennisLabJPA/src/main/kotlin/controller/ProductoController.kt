package controller

import models.Producto
import repositories.producto.IProductoRepository
import repositories.producto.ProductoRepositoryImplement

class ProductoController(
    private val productoRepository: IProductoRepository = ProductoRepositoryImplement()
) {
    /**
     * Find all
     *
     * @return Devuelve todos los productos encontrados.
     */
    fun findAll(): List<Producto> {
        return productoRepository.findAll()
    }

    /**
     * Find by id
     *
     * @param id
     * @return Devuelve el producto por su id.
     */
    fun findById(id: Int): Producto? {
        return productoRepository.findById(id)
    }

    /**
     * Save
     *
     * @param producto
     * @return Devuelve el producto insertado.
     */
    fun save(producto: Producto): Producto {
        return productoRepository.save(producto)
    }

    /**
     * Delete
     *
     * @param producto
     * @return True, si se ha borrado, False si no lo ha borrado.
     */
    fun delete(producto: Producto): Boolean {
        return productoRepository.delete(producto)
    }


}