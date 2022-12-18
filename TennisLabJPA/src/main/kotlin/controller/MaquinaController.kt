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

    fun findAllMaquinaEncordar(): List<MaquinaEncordar> {
        return maquinaEncordarRepository.findAll()
    }

    fun findByIdMaquinaEncordar(id: Int): MaquinaEncordar? {
        return maquinaEncordarRepository.findById(id)
    }

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