package repositories

/**
 * I c r u d repository
 *
 * @param T
 * @param ID
 * @constructor Create empty I c r u d repository
 */
interface ICRUDRepository<T, ID> {
    /**
     * Find all
     *
     * @return
     */
    fun findAll(): List<T>

    /**
     * Find by id
     *
     * @param id
     * @return
     */
    fun findById(id: ID): T?

    /**
     * Save
     *
     * @param entity
     * @return
     */
    fun save(entity: T): T

    /**
     * Delete
     *
     * @param entity
     * @return
     */
    fun delete(entity: T): Boolean
}