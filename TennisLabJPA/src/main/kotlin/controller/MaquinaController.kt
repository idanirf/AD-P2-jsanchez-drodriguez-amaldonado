package controller

import models.MaquinaEncordar
import models.MaquinaPersonalizar
import repositories.maquina.IMaquinaEncordarRepository
import repositories.maquina.IMaquinaPersonalizarRepository
import repositories.maquina.MaquinaEncordarRepositoryImplement
import repositories.maquina.MaquinaPersonalizarRepositoryImplement

class MaquinaController(
    private val maquinaEncordarRepository: IMaquinaEncordarRepository = MaquinaEncordarRepositoryImplement(),
    private val maquinaPersonalizarRepository: IMaquinaPersonalizarRepository = MaquinaPersonalizarRepositoryImplement()
) {
    /**
     * Find all maquina encordar
     *
     * @return Devuelve los objetos desde su base de datos
     */
    fun findAllMaquinaEncordar(): List<MaquinaEncordar> {
        return maquinaEncordarRepository.findAll()
    }

    /**
     * Find by id maquina encordar
     *
     * @param id
     * @return devuelve las máquinas encordar insertadas
     */
    fun findByIdMaquinaEncordar(id: Int): MaquinaEncordar? {
        return maquinaEncordarRepository.findById(id)
    }

    /**
     * Save maquina encordar
     *
     * @param maquinaEncordar
     * @return devuelve las máquinas encordar guardadas.
     */
    fun saveMaquinaEncordar(maquinaEncordar: MaquinaEncordar): MaquinaEncordar {
        return maquinaEncordarRepository.save(maquinaEncordar)
    }

    fun deleteMaquinaEncordar(maquinaEncordar: MaquinaEncordar): Boolean {
        return maquinaEncordarRepository.delete(maquinaEncordar)
    }

    fun findAllMaquinaPersonalizar(): List<MaquinaPersonalizar> {
        return maquinaPersonalizarRepository.findAll()
    }

    fun findByIdMaquinaPersonalizar(id: Int): MaquinaPersonalizar? {
        return maquinaPersonalizarRepository.findById(id)
    }

    fun saveMaquinaPersonalizar(maquinaPersonalizar: MaquinaPersonalizar): MaquinaPersonalizar {
        return maquinaPersonalizarRepository.save(maquinaPersonalizar)
    }

    fun deleteMaquinaPersonalizar(maquinaPersonalizar: MaquinaPersonalizar): Boolean {
        return maquinaPersonalizarRepository.delete(maquinaPersonalizar)
    }
}