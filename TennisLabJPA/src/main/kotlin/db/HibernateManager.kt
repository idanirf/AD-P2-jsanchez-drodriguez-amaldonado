package db

import mu.KotlinLogging
import java.io.Closeable
import java.sql.SQLException
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.Persistence


val logger = KotlinLogging.logger {}

object HibernateManager : Closeable {
    private var entityManagerFactory = Persistence.createEntityManagerFactory("TennisLab")
    lateinit var manager: EntityManager
    private lateinit var transaction: EntityTransaction

    init {
        entityManagerFactory = Persistence.createEntityManagerFactory("TennisLab")
        manager = entityManagerFactory.createEntityManager()
        transaction = manager.transaction
    }

    fun open() {
        logger.debug { "Iniciando EntityManagerFactory" }
        manager = entityManagerFactory.createEntityManager()
        transaction = manager.transaction
    }

    override fun close() {
        logger.debug { "Cerrando EntityManager" }
        manager.close()
    }

    fun query(operations: () -> Unit) {
        open()
        try {
            operations()
        } catch (e: SQLException) {
            logger.error { "Error en la consulta: ${e.message}" }
        } finally {
            close()
        }
    }

    /**
     * Realiza unas operaciones en una transacción
     * @param operations operaciones a realizar
     * @throws SQLException si no se ha podido realizar la transacción
     */
    fun transaction(operations: () -> Unit) {
        open()
        try {
            logger.debug { "Transaction iniciada" }
            transaction.begin()
            // Aquí las operaciones
            operations()
            transaction.commit()
            logger.debug { "Transaction finalizada" }
        } catch (e: SQLException) {
            transaction.rollback()
            logger.error { "Error en la transacción: ${e.message}" }
            throw SQLException(e)
        } finally {
            close()
        }
    }
}