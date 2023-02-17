package db

import config.AppConfig
import entitities.*
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

private val logger = KotlinLogging.logger {}

object DataBaseManager {
    lateinit var appConfig: AppConfig
    fun init(appConfig: AppConfig) {
        this.appConfig = appConfig
        logger.debug("Initializing database")
        Database.connect(
            url = appConfig.jdbcUrl,
            driver = appConfig.jdbcDriverClassName,
            user = appConfig.jdbcUserName,
            password = appConfig.jdbcPassword
        )

        logger.debug("Database initialized successfully")

        if (appConfig.jdbcCreateTables) {
            createTables()
        }
    }

    private fun createTables() = transaction {
        logger.debug("Creating tables")

        if (appConfig.jdbcshowSQL)
            addLogger(StdOutSqlLogger)

        SchemaUtils.create(
            TablaMaquinaEncordar,
            TablaMaquinaPersonalizar,
            TablaPedido,
            TablaProducto,
            TablaTareaEncordado,
            TablaTareaPersonalizacion,
            TablaTurno,
            TablaUsuario
        )
        logger.debug("Tables created")
    }

    fun dropTables() = transaction {
        logger.debug { "Eliminando tablas de la base de datos" }

        if (appConfig.jdbcshowSQL)
            addLogger(StdOutSqlLogger)

        val tables = arrayOf(
            TablaMaquinaEncordar,
            TablaMaquinaPersonalizar,
            TablaPedido,
            TablaProducto,
            TablaTareaEncordado,
            TablaTareaPersonalizacion,
            TablaTurno,
            TablaUsuario
        )

        SchemaUtils.drop(*tables)
        logger.debug { "Tablas eliminadas (${tables.size}): ${tables.joinToString { it.tableName }}" }
    }

    fun clearTables() = transaction {
        logger.debug { "clear tables" }

        if (appConfig.jdbcshowSQL)
            addLogger(StdOutSqlLogger)

        val tables = arrayOf(
            TablaMaquinaEncordar,
            TablaMaquinaPersonalizar,
            TablaPedido,
            TablaProducto,
            TablaTareaEncordado,
            TablaTareaPersonalizacion,
            TablaTurno,
            TablaUsuario
        )

        tables.forEach {
            it.deleteAll()
        }
        logger.debug { "Limpiar tablas (${tables.size}): ${tables.joinToString { it.tableName }}" }
    }
}