import db.HibernateManager
import utils.ApplicationProperties
import utils.logger

fun main(){
    initDatabase()

}

fun initDatabase() {
    val properties = ApplicationProperties()
    logger.debug { "Leyendo fichero de configuración..." + properties.readProperty("app.title") }
    HibernateManager.open()
    HibernateManager.close()
}
