package controller

import entitities.MaquinasEncordarDAO
import entitities.MaquinaPersonalizarDAO
import entitities.TurnoDAO
import models.MaquinaEncordar
import models.MaquinaPersonalizar
import repositories.maquina.MaquinaEncordarRepositoryImplement
import repositories.maquina.MaquinaPersonalizarRepositoryImplement

/**
 * Maquinas controller: En este controlador están las máquinas de encordar y las máquinas de personalizar.
 *
 * @property maquinaEncordarRepository
 * @property maquinaPersonalizarRepository
 * @constructor Create empty Maquinas controller
 */
class MaquinasController (
    private val maquinaEncordarRepository: MaquinaEncordarRepositoryImplement = MaquinaEncordarRepositoryImplement(
        MaquinasEncordarDAO,
        TurnoDAO
    ),
    private val maquinaPersonalizarRepository: MaquinaPersonalizarRepositoryImplement = MaquinaPersonalizarRepositoryImplement(
        MaquinaPersonalizarDAO,
        TurnoDAO
    )

){

    /**
     * Find all maquinas encordar: Encuentra todas las máquinas de encordar
     *
     * @return Retorna todas las máquinas de encordar
     */
    fun findAllMaquinasEncordar(): List<MaquinaEncordar> {
        return maquinaEncordarRepository.findAll()
    }

    /**
     * Find by id maquina encordar: Encuentra una máquina encordar por su id
     *
     * @param id
     * @return Retorna la maquina de encordar
     */
    fun findByIdMaquinaEncordar(id: Int): MaquinaEncordar? {
        return maquinaEncordarRepository.findById(id)
    }


    /**
     * Save maquina encordar: Guarda una máquina de encordar
     *
     * @param entity
     * @return Devuelve la maquina guardada
     */
    fun saveMaquinaEncordar(entity: MaquinaEncordar): MaquinaEncordar {
        return maquinaEncordarRepository.save(entity)
    }

    /**
     * Delete máquina encordar: Borra una máquina de encordar
     *
     * @param entity
     * @return Devuelve la máquina encordar borrada
     */
    fun deleteMaquinaEncordar(entity: MaquinaEncordar): Boolean {
        return maquinaEncordarRepository.delete(entity)
    }

    /**
     * Find all maquina personalizar: Busca todas las máquinas de personalizar.
     *
     * @return las máquina personalizadas
     */
    fun findAllMaquinaPersonalizar(): List<MaquinaPersonalizar> {
        return maquinaPersonalizarRepository.findAll()
    }

    /**
     * Find all maquina personalizar
     *
     * @param id
     * @return Todas las máquinas de personalizar
     */
    fun findByIdMaquinaPersonalizar(id: Int): MaquinaPersonalizar? {
        return maquinaPersonalizarRepository.findById(id)
    }

    /**
     * Save maquina personalizar
     *
     * @param entity
     * @return las máquinas insertadas de personalizar
     */
    fun saveMaquinaPersonalizar(entity: MaquinaPersonalizar): MaquinaPersonalizar {
        return maquinaPersonalizarRepository.save(entity)
    }

    /**
     * Delete maquina personalizar
     *
     * @param entity
     * @return
     */
    fun deleteMaquinaPersonalizar(entity: MaquinaPersonalizar): Boolean {
        return maquinaPersonalizarRepository.delete(entity)
    }

}