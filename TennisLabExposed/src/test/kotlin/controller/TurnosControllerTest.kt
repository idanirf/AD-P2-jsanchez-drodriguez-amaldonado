package controller

import exception.TurnoException
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Turno
import models.Usuario
import models.enums.TipoUsuario
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import repositories.turno.TurnoRepositoryImplement
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockKExtension::class)
class TurnosControllerTest {
    @MockK
    lateinit var turnoRepositoryImplement: TurnoRepositoryImplement

    @InjectMockKs
    lateinit var turnosController: TurnosController
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

    @Test
    fun findAllTurnos() {
        every { turnoRepositoryImplement.findAll() } returns listOf(turno)
        val res = turnosController.findAll()
        assert(res == listOf(turno))
        verify(exactly = 1) { turnoRepositoryImplement.findAll() }
    }

    @Test
    fun findByIdTurno() {
        every { turnoRepositoryImplement.findById(turno.id) } returns turno
        val res = turnosController.findById(turno.id)
        assert(res == turno)
        verify(exactly = 1) { turnoRepositoryImplement.findById(turno.id) }
    }



    @Test
    fun saveTurno() {
        every { turnoRepositoryImplement.save(turno) } returns turno
        val res = turnosController.save(turno)
        assert(res == turno)
        verify(exactly = 1) { turnoRepositoryImplement.save(turno) }
    }

    @Test
    fun deleteTurno() {
        every { turnoRepositoryImplement.delete(turno) } returns true
        val res = turnosController.delete(turno)
        assert(res)
        verify(exactly = 1) { turnoRepositoryImplement.delete(turno) }
    }


    @Test
    fun deleteNoExisteTurno() {
        every { turnoRepositoryImplement.delete(turno) } throws TurnoException("Error: No existe turno con id ${turno.id}")
        val res = assertThrows<TurnoException> { turnosController.delete(turno) }
        assert(res.message == "Error: No existe turno con id ${turno.id}")
        verify(exactly = 1) { turnoRepositoryImplement.delete(turno) }
    }
    @Test
    fun findByIdNoExisteTurno() {
        every { turnoRepositoryImplement.findById(turno.id) } throws TurnoException("Error: No existe turno con id ${turno.id}")
        val res = assertThrows<TurnoException> { turnosController.findById(turno.id) }
        assert(res.message == "Error: No existe turno con id ${turno.id}")
        verify(exactly = 1) { turnoRepositoryImplement.findById(turno.id) }
    }
}