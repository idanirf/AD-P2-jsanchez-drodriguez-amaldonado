package controller

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import io.mockk.every
import models.MaquinaEncordar
import models.MaquinaPersonalizar
import models.Turno
import models.Usuario
import models.enums.TipoEncordaje
import models.enums.TipoUsuario
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import repositories.maquina.MaquinaEncordarRepositoryImplement
import repositories.maquina.MaquinaPersonalizarRepositoryImplement
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockKExtension::class)
internal class MaquinaControllerTest {
    @MockK
    lateinit var maquinaEncordarRepository: MaquinaEncordarRepositoryImplement

    @MockK
    lateinit var maquinaPersonalizarRepository: MaquinaPersonalizarRepositoryImplement

    @InjectMockKs
    lateinit var maquinaController: MaquinaController

    private val usuario = Usuario(
        id = 1,
        uuid = UUID.randomUUID(),
        nombre = "Marcelo",
        apellido = "Alvarez",
        correo = "marcelo@alvarez.com",
        password = "1234",
        tipoUsuario = TipoUsuario.ADMINISTRADOR

    )
    private val turno = Turno(
        id = 0,
        uuid = UUID.randomUUID(),
        fechaInicio = LocalDateTime.of(2022, 10, 14, 9, 10),
        fechaFinal = LocalDateTime.of(2022, 11, 20, 10, 20),
        usuario = usuario
    )
    private val maquinaEncordar = MaquinaEncordar(
        id = 0,
        uuid = UUID.randomUUID(),
        marca = "HEAD",
        modelo = "2020",
        fechaAdquisicion = LocalDate.of(2022, 3, 5),
        numeroSerie = 20,
        tipo = TipoEncordaje.MANUAL,
        tensionMaxima = 25.10,
        tensionMinima = 22.60,
        turno = turno
    )
    private val maquinaPersonalizar = MaquinaPersonalizar(
        id = 0,
        uuid = UUID.randomUUID(),
        marca = "Signum Pro",
        modelo = "30",
        fechaAdquisicion = LocalDate.of(2022, 4, 15),
        numeroSerie = 7,
        swingweight = true,
        balance = 300.80,
        rigidez = 100.60,
        turno = turno
    )

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun findAllMaquinasPersonalizar() {
        every { maquinaPersonalizarRepository.findAll() } returns listOf(maquinaPersonalizar)
        val res = maquinaController.findAllMaquinaPersonalizar()
        assert(res == listOf(maquinaPersonalizar))
        verify(exactly = 1) { maquinaPersonalizarRepository.findAll() }
    }

    @Test
    fun findByIdMaquinaPersonalizar() {
        every { maquinaPersonalizarRepository.findById(maquinaPersonalizar.id) } returns maquinaPersonalizar
        val res = maquinaController.findByIdMaquinaPersonalizar(maquinaPersonalizar.id)
        assert(res == maquinaPersonalizar)
        verify(exactly = 1) { maquinaPersonalizarRepository.findById(maquinaPersonalizar.id) }

    }

    @Test
    fun findByIdNoExisteMaquinaPersonalizar() {
        every { maquinaPersonalizarRepository.findById(maquinaPersonalizar.id) } returns null

        val res = maquinaController.findByIdMaquinaPersonalizar(maquinaPersonalizar.id)
        assert(res == null)
        verify(exactly = 1) { maquinaPersonalizarRepository.findById(maquinaPersonalizar.id) }
    }
    @Test
    fun saveMaquinaPersonalizar(){
        every { maquinaPersonalizarRepository.save(maquinaPersonalizar) } returns maquinaPersonalizar
        val res = maquinaController.saveMaquinaPersonalizar(maquinaPersonalizar)
        assert(res==maquinaPersonalizar)
        verify (exactly = 1){maquinaPersonalizarRepository.save(maquinaPersonalizar)}
    }
    @Test
    fun deleteMaquinaPersonalizar(){
        every { maquinaPersonalizarRepository.delete(maquinaPersonalizar) } returns false
        val res = maquinaController.deleteMaquinaPersonalizar(maquinaPersonalizar)
        assert(!res)
        verify (exactly = 1){maquinaPersonalizarRepository.delete(maquinaPersonalizar)}
    }
    @Test
    fun findAllMaquinaEncordar(){
        every {maquinaEncordarRepository.findAll()} returns listOf(maquinaEncordar)
        val res = maquinaController.findAllMaquinaEncordar()
        assert(res == listOf(maquinaEncordar))
        verify (exactly = 1){maquinaEncordarRepository.findAll()}
    }
    @Test
    fun findByIdMaquinaEncordar(){
        every { maquinaEncordarRepository.findById(maquinaEncordar.id) } returns maquinaEncordar
        val res = maquinaController.findByIdMaquinaEncordar(maquinaEncordar.id)
        assert(res == maquinaEncordar)
        verify (exactly = 1){maquinaEncordarRepository.findById(maquinaEncordar.id)}
    }
    @Test
    fun findByIdNoexisteMaquinaEncordar(){
        every{maquinaEncordarRepository.findById(maquinaEncordar.id)} returns null
        val res = maquinaController.findByIdMaquinaEncordar(maquinaEncordar.id)
        assert(res==null)
        verify (exactly = 1){maquinaEncordarRepository.findById(maquinaEncordar.id)}
    }
    @Test
    fun saveMaquinaEncordar(){
        every { maquinaEncordarRepository.save(maquinaEncordar) } returns maquinaEncordar
        val res = maquinaController.saveMaquinaEncordar(maquinaEncordar)
        assert(res == maquinaEncordar)
        verify (exactly = 1){maquinaEncordarRepository.save(maquinaEncordar)}
    }
    @Test
    fun deleteMaquinaEncordar(){
        every { maquinaEncordarRepository.delete(maquinaEncordar) } returns true
        val res = maquinaController.deleteMaquinaEncordar(maquinaEncordar)
        assert(res)
        verify (exactly = 1){maquinaEncordarRepository.delete(maquinaEncordar)}
    }


}