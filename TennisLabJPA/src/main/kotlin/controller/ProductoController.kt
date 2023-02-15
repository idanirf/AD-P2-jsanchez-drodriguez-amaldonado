package controller

import models.Producto
import repositories.producto.IProductoRepository
import repositories.producto.ProductoRepositoryImplement

class ProductoController(
    private val productoRepository: IProductoRepository = ProductoRepositoryImplement()
) {

    fun findAll(): List<Producto> {
        return productoRepository.findAll()
    }

    fun findById(id: Int): Producto? {
        return productoRepository.findById(id)
    }

    fun save(producto: Producto): Producto {
        return productoRepository.save(producto)
    }

    fun delete(producto: Producto): Boolean {
        return productoRepository.delete(producto)
    }


}