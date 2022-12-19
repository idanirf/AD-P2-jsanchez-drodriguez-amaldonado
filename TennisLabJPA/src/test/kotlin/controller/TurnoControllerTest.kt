package controller

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Turno
import models.Usuario
import models.enums.TipoUsuario
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import repositories.turno.TurnoRepositoryImplement
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockKExtension::class)
class TurnoControllerTest {
    @MockK
    lateinit var turnosRepositoryImplement: TurnoRepositoryImplement
    @InjectMockKs
    lateinit var turnoController: TurnoController
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
    init {
        MockKAnnotations.init(this)
    }
    @Test
    fun findAllTurnos(){
        every { turnosRepositoryImplement.findAll() }returns listOf(turno)
        val res = turnoController.findAll()
        assert(res == listOf(turno))
        verify(exactly = 1) {turnosRepositoryImplement.findAll()  }
    }
    @Test
    fun findByIdTurno(){
        every { turnosRepositoryImplement.findById(turno.id) }returns turno
        val res = turnoController.findById(turno.id)
        assert(res == turno)
        verify(exactly = 1) {turnosRepositoryImplement.findById(turno.id)  }
    }
    @Test
    fun findByIdNoExiste(){
        every{turnosRepositoryImplement.findById(turno.id)}returns null
        val res = turnoController.findById(turno.id)
        verify(exactly = 1) {turnosRepositoryImplement.findById(turno.id)  }
    }
    @Test
    fun saveTurno(){
        every { turnosRepositoryImplement.save(turno) }returns turno
        val res = turnoController.save(turno)
        assert(res==turno)
        verify(exactly = 1) {turnosRepositoryImplement.save(turno)  }
    }
    @Test
    fun deleteTurno(){
        every { turnosRepositoryImplement.delete(turno) }returns true
        val res = turnoController.delete(turno)
        assert(res)
        verify(exactly = 1) {turnosRepositoryImplement.delete(turno)  }
    }
    @Test
    fun deleteNoExisteTurno(){
        every { turnosRepositoryImplement.delete(turno) }returns false
        val res = turnoController.delete(turno)
        assert(!res)
        verify(exactly = 1) {turnosRepositoryImplement.delete(turno)  }
    }
}