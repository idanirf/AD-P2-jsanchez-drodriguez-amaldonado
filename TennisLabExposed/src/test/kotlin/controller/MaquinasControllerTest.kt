package controller

import exception.MaquinaEncordarException
import exception.MaquinaPersonalizacionException
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.MaquinaEncordar
import models.MaquinaPersonalizar
import models.Turno
import models.Usuario
import models.enums.TipoEncordaje
import models.enums.TipoUsuario
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import repositories.maquina.MaquinaEncordarRepositoryImplement
import repositories.maquina.MaquinaPersonalizarRepositoryImplement
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockKExtension::class)
internal class MaquinasControllerTest {
    @MockK
    lateinit var maquinaPersonalizarRepositoryImplement: MaquinaPersonalizarRepositoryImplement

    @MockK
    lateinit var maquinaEncordarRepositoryImplement: MaquinaEncordarRepositoryImplement

    @InjectMockKs
    lateinit var maquinasController: MaquinasController

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

    @Test
    fun findAllMaquinasPersonalizar() {
        every { maquinaPersonalizarRepositoryImplement.findAll() } returns listOf(maquinaPersonalizar)
        val res = maquinasController.findAllMaquinaPersonalizar()
        assert(res == listOf(maquinaPersonalizar))
        verify(exactly = 1) { maquinaPersonalizarRepositoryImplement.findAll() }
    }

    @Test
    fun findByIdMaquinaPersonalizarById() {
        every { maquinaPersonalizarRepositoryImplement.findById(maquinaPersonalizar.id) } returns maquinaPersonalizar
        val res = maquinasController.findByIdMaquinaPersonalizar(maquinaPersonalizar.id)
        assert(res == maquinaPersonalizar)
        verify(exactly = 1) { maquinaPersonalizarRepositoryImplement.findById(maquinaPersonalizar.id) }
    }

    @Test
    fun findByIdMaquinaPersonzalizarIdNoExiste() {
        every { maquinaPersonalizarRepositoryImplement.findById(maquinaPersonalizar.id) } throws MaquinaPersonalizacionException(
            "Máquina con id ${maquinaPersonalizar.id} no existe"
        )

        val res = assertThrows<MaquinaPersonalizacionException> {
            maquinasController.findByIdMaquinaPersonalizar(
                maquinaPersonalizar.id
            )
        }

        assert(res.message == "Máquina con id ${maquinaPersonalizar.id} no existe")
        verify(exactly = 1) { maquinaPersonalizarRepositoryImplement.findById(maquinaPersonalizar.id) }
    }

    @Test
    fun saveMaquinaPersonalizar() {
        every { maquinaPersonalizarRepositoryImplement.save(maquinaPersonalizar) } returns maquinaPersonalizar
        val res = maquinasController.saveMaquinaPersonalizar(maquinaPersonalizar)
        assert(res == maquinaPersonalizar)
        verify(exactly = 1) { maquinaPersonalizarRepositoryImplement.save(maquinaPersonalizar) }
    }

    @Test
    fun deleteMaquinaPersonalizar() {
        every { maquinaPersonalizarRepositoryImplement.delete(maquinaPersonalizar) } returns true
        val res = maquinasController.deleteMaquinaPersonalizar(maquinaPersonalizar)
        assert(res)
        verify(exactly = 1) { maquinaPersonalizarRepositoryImplement.delete(maquinaPersonalizar) }
    }


    @Test
    fun deleteMaquinaPersonalizarNoExiste() {
        every { maquinaPersonalizarRepositoryImplement.delete(maquinaPersonalizar) } throws MaquinaPersonalizacionException(
            "Máquina con id ${maquinaPersonalizar.id} no existe"
        )

        val res = assertThrows<MaquinaPersonalizacionException> {
            maquinasController.deleteMaquinaPersonalizar(maquinaPersonalizar)
        }

        assert(res.message == "Máquina con id ${maquinaPersonalizar.id} no existe")

        verify(exactly = 1) { maquinaPersonalizarRepositoryImplement.delete(maquinaPersonalizar) }
    }

    @Test
    fun findAllMaquinaEncordado() {
        every { maquinaEncordarRepositoryImplement.findAll() } returns listOf(maquinaEncordar)

        val res = maquinasController.findAllMaquinasEncordar()

        assert(res == listOf(maquinaEncordar))

        verify(exactly = 1) { maquinaEncordarRepositoryImplement.findAll() }
    }

    @Test
    fun findByIdMaquinaEncordar() {
        every { maquinaEncordarRepositoryImplement.findById(maquinaEncordar.id) } returns maquinaEncordar

        val res = maquinasController.findByIdMaquinaEncordar(maquinaEncordar.id)

        assert(res == maquinaEncordar)

        verify(exactly = 1) { maquinaEncordarRepositoryImplement.findById(maquinaEncordar.id) }
    }

    @Test
    fun saveMaquinaEncordar() {
        every { maquinaEncordarRepositoryImplement.save(maquinaEncordar) } returns maquinaEncordar

        val res = maquinasController.saveMaquinaEncordar(maquinaEncordar)

        assert(res == maquinaEncordar)

        verify(exactly = 1) { maquinaEncordarRepositoryImplement.save(maquinaEncordar) }
    }

    @Test
    fun delete() {
        every { maquinaEncordarRepositoryImplement.delete(maquinaEncordar) } returns true

        val res = maquinasController.deleteMaquinaEncordar(maquinaEncordar)

        assert(res)

        verify(exactly = 1) { maquinaEncordarRepositoryImplement.delete(maquinaEncordar) }
    }
}