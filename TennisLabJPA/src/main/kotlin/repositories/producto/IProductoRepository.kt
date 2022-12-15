package repositories.producto

import models.Producto
import repositories.ICRUDRepository

    interface IProductoRepository: ICRUDRepository<Producto, Int> {
        // Implementamos los métodos que nos vayan haciendo falta.
    }

