package controller

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Pedido
import models.TareaEncordado
import models.TareaPersonalizacion
import models.Usuario
import models.enums.NumeroNudos
import models.enums.TipoEstado
import models.enums.TipoUsuario
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import repositories.tareas.TareaEncordadoRepositoryImplement
import repositories.tareas.TareaPersonalizacionRepositoryImplement
import java.time.LocalDate
import java.util.*

@ExtendWith(MockKExtension::class)
class TareaControllerTest {
    @MockK
    lateinit var tareaEncordadoRepositoryImplement: TareaEncordadoRepositoryImplement

    @MockK
    lateinit var tareaPersonalizacionRepositoryImplement: TareaPersonalizacionRepositoryImplement

    @InjectMockKs
    lateinit var tareaController: TareaController

    private val usuario = Usuario(
        id = 0,
        uuid = UUID.randomUUID(),
        nombre = "Alfonso",
        apellido = "Cabello",
        correo = "alfonso@cabello.com",
        password = "1234",
        tipoUsuario = TipoUsuario.USUARIO
    )
    private val pedido = Pedido(
        id = 0,
        uuid = UUID.randomUUID(),
        estado = TipoEstado.TERMINADO,
        fechaEntrada = LocalDate.of(2022, 3, 15),
        fechaSalidaProgramada = LocalDate.of(2022, 5, 10),
        fechaEntrega = LocalDate.of(2022, 6, 7),
        precio = 10.20,
        usuario = usuario
    )
    private val tareaPersonalizacion = TareaPersonalizacion(
        id = 0,
        uuid = UUID.randomUUID(),
        rigidez = 69.69,
        peso = 0.20,
        balance = 327.10,
        precio = 99.99,
        pedido = pedido
    )
    private val tareaEncordado = TareaEncordado(
        id = 1,
        uuid = UUID.randomUUID(),
        precio = 100.50,
        tensionVertical = 22.50,
        cordajeVertical = "Wilson",
        tensionHorizontal = 26.10,
        cordajeHorizontal = "Wilson",
        nudos = NumeroNudos.DOS,
        pedido = pedido
    )

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun findAllTareasPersonalizacion() {
        every { tareaPersonalizacionRepositoryImplement.findAll() } returns listOf(tareaPersonalizacion)
        val res = tareaController.findAllTareaPersonalizacion()
        assert(res == listOf(tareaPersonalizacion))
        verify(exactly = 1) { tareaPersonalizacionRepositoryImplement.findAll() }
    }

    @Test
    fun findByIdTareaPersonalizacion() {
        every { tareaPersonalizacionRepositoryImplement.findById(tareaPersonalizacion.id) } returns tareaPersonalizacion
        val res = tareaController.findByIdTareaPersonalizacion(tareaPersonalizacion.id)
        assert(res == tareaPersonalizacion)
        verify(exactly = 1) {tareaPersonalizacionRepositoryImplement.findById(tareaPersonalizacion.id)  }
    }

    @Test
    fun findByIdNoExisteTareaPersonalizacion() {
        every { tareaPersonalizacionRepositoryImplement.findById(tareaPersonalizacion.id) } returns null
        val res = tareaController.findByIdTareaPersonalizacion(tareaPersonalizacion.id)
        assert(res == null)
        verify(exactly = 1) { tareaPersonalizacionRepositoryImplement.findById(tareaPersonalizacion.id) }

    }

    @Test
    fun saveTareaPersonalizacion() {
        every { tareaPersonalizacionRepositoryImplement.save(tareaPersonalizacion) } returns tareaPersonalizacion
        val res = tareaController.saveTareaPersonalizacion(tareaPersonalizacion)
        assert(res == tareaPersonalizacion)
        verify(exactly = 1) { tareaPersonalizacionRepositoryImplement.save(tareaPersonalizacion) }
    }

    @Test
    fun deleteTareaPersonalizacion() {
        every { tareaPersonalizacionRepositoryImplement.delete(tareaPersonalizacion) } returns true
        val res = tareaController.deleteTareaPersonalizacion(tareaPersonalizacion)
        assert(res)
        verify(exactly = 1) { tareaPersonalizacionRepositoryImplement.delete(tareaPersonalizacion) }
    }

    @Test
    fun findAllTareaEncordado() {
        every { tareaEncordadoRepositoryImplement.findAll() } returns listOf(tareaEncordado)
        val res = tareaController.findAllTareaEncordado()
        assert(res == listOf(tareaEncordado))
        verify(exactly = 1) { tareaEncordadoRepositoryImplement.findAll() }
    }

    @Test
    fun findByIdTareaEncordado() {
        every { tareaEncordadoRepositoryImplement.findById(tareaEncordado.id) } returns tareaEncordado
        val res =tareaController.findByIdTareaEncordado(tareaEncordado.id)
        assert(res == tareaEncordado)
        verify(exactly = 1) {tareaEncordadoRepositoryImplement.findById(tareaEncordado.id)  }
    }
    @Test
    fun findByIdNoExisteTareaEncordado(){
        every { tareaEncordadoRepositoryImplement.findById(tareaEncordado.id) }returns null
        val res = tareaController.findByIdTareaEncordado(tareaEncordado.id)
        assert(res == null)
        verify(exactly = 1) {tareaEncordadoRepositoryImplement.findById(tareaEncordado.id)  }
    }
    @Test
    fun saveTareaEncordador(){
        every { tareaEncordadoRepositoryImplement.save(tareaEncordado) } returns tareaEncordado
        val res = tareaController.saveTareaEncordado(tareaEncordado)
        assert(res==tareaEncordado)
        verify(exactly = 1) {tareaEncordadoRepositoryImplement.save(tareaEncordado)  }
    }
    @Test
    fun deleteTareaPersonalizar(){
        every { tareaEncordadoRepositoryImplement.delete(tareaEncordado) } returns false
        val res = tareaController.deleteTareaEncordado(tareaEncordado)
        assert(!res)
        verify(exactly = 1) {tareaEncordadoRepositoryImplement.delete(tareaEncordado)  }
    }


}