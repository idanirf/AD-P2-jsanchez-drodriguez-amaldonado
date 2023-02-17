package mapper

import entitities.MaquinasEncordarDAO
import entitities.MaquinaPersonalizarDAO
import models.MaquinaEncordar
import models.MaquinaPersonalizar
import models.enums.TipoEncordaje

/**
 * From maquina encordar d a o to maquina encordar
 *
 * @return el objeto ya en modelo
 */
fun MaquinasEncordarDAO.fromMaquinaEncordarDAOToMaquinaEncordar(): MaquinaEncordar {
    return MaquinaEncordar(
        id = id.value,
        uuid = uuid,
        marca = marca,
        modelo = modelo,
        fechaAdquisicion = fechaAdquisicion,
        numeroSerie = numeroSerie,
        tipo = TipoEncordaje.from(tipo),
        turno = turno.fromTurnoDAOToTurno(),
        tensionMaxima = tensionMaxima,
        tensionMinima = tensionMinima,
    )
}

/**
 * From maquina personalizar d a o to maquina personalizar
 *
 * @return el objeto ya en modelo.
 */
fun MaquinaPersonalizarDAO.fromMaquinaPersonalizarDAOToMaquinaPersonalizar(): MaquinaPersonalizar {
    return MaquinaPersonalizar(
        id = id.value,
        uuid = uuid,
        marca = marca,
        modelo = modelo,
        fechaAdquisicion = fechaAdquisicion,
        numeroSerie = numeroSerie,
        swingweight = swingweight,
        balance = balance,
        rigidez = rigidez,
        turno = turno.fromTurnoDAOToTurno(),



    )
}