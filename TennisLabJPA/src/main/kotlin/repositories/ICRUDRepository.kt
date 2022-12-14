package repositories

interface ICRUDRepository<T,ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun save(entity: T): T
    fun delete(id: ID): Boolean

    //fun update(entity: T): T //TODO vemos si es necesario que este aquí
}