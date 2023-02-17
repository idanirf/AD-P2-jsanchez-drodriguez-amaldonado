package config

import mu.KotlinLogging
import java.io.FileInputStream
import java.util.*

private val logger = KotlinLogging.logger {}

/**
 * App config: Clase que enlaza con el config.properties y con el DataBaseManager
 *
 * @property nombre
 * @property version
 * @property jdbcUrl
 * @property jdbcUserName
 * @property jdbcPassword
 * @property jdbcDriverClassName
 * @property jdbcMaximumPoolSize
 * @property jdbcCreateTables
 * @property jdbcshowSQL
 * @constructor Create empty App config
 */
data class AppConfig(
    val nombre: String,
    val version: String,
    val jdbcUrl: String,
    val jdbcUserName: String,
    val jdbcPassword: String,
    val jdbcDriverClassName: String,
    val jdbcMaximumPoolSize: Int = 10,
    val jdbcCreateTables: Boolean = true,
    val jdbcshowSQL: Boolean = true,
) {
    companion object {
        val DEFAULT = AppConfig(
            nombre = "app",
            version = "1.0.0",
            jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;",
            jdbcUserName = "tenista",
            jdbcPassword = "tenista",
            jdbcDriverClassName = "org.h2.Driver",
            jdbcMaximumPoolSize = 10,
            jdbcCreateTables = true,
            jdbcshowSQL = true,
        )

        /**
         * From properties file: Método que obtiene los datos de la configuración desde el config.properties
         *
         * @param fileName
         * @return AppConfig
         */
        fun fromPropertiesFile(fileName: String): AppConfig {
            logger.debug { "Loading properties from file: $fileName" }
            val properties = Properties()
            properties.load(FileInputStream(fileName))
            return AppConfig(
                nombre = properties.getProperty("nombre"),
                version = properties.getProperty("version"),
                jdbcUrl = properties.getProperty("jdbc.url"),
                jdbcUserName = properties.getProperty("jdbc.username"),
                jdbcPassword = properties.getProperty("jdbc.password"),
                jdbcDriverClassName = properties.getProperty("jdbc.driverClassName"),
                jdbcMaximumPoolSize = properties.getProperty("jdbc.maximumPoolSize").toInt(),
                jdbcCreateTables = properties.getProperty("jdbc.createTables").toBoolean(),
                jdbcshowSQL = properties.getProperty("jdbc.showSQL").toBoolean(),
            )
        }
    }
}