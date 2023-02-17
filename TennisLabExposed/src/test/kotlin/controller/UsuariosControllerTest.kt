package controller

import exception.UsuarioException
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Usuario
import models.enums.TipoUsuario
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import repositories.usuario.UsuarioRepositoryImplement
import java.util.*

@ExtendWith(MockKExtension::class)
class UsuariosControllerTest {

    @MockK
    lateinit var usuarioRepositoryImplement: UsuarioRepositoryImplement

    @InjectMockKs
    lateinit var usuariosController: UsuariosController

    private val usuario= Usuario(
        id = 1,
        uuid = UUID.randomUUID(),
        nombre = "Marcelo",
        apellido = "Alvarez",
        correo = "marcelo@alvarez.com",
        password = "1234",
        tipoUsuario = TipoUsuario.ADMINISTRADOR
    )

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun findAllUsuarios() {
        every { usuarioRepositoryImplement.findAll() } returns listOf(usuario)
        val res = usuariosController.findAll()
        assert(res == listOf(usuario))
        verify(exactly = 1) { usuarioRepositoryImplement.findAll() }
    }

    @Test
    fun findByIdUsuario() {
        every { usuarioRepositoryImplement.findById(usuario.id) } returns usuario
        val res = usuariosController.findById(usuario.id)
        assert(res == usuario)
        verify(exactly = 1) { usuarioRepositoryImplement.findById(usuario.id) }

    }

    @Test
    fun findByIdNoExisteUsuario() {
        every { usuarioRepositoryImplement.findById(usuario.id) } throws UsuarioException("Error: No encontrado usuario con id: ${usuario.id}")
        val res = assertThrows<UsuarioException> {
            usuariosController.findById(usuario.id)
        }
        assert(res.message == "Error: No encontrado usuario con id: ${usuario.id}")
        verify(exactly = 1) { usuarioRepositoryImplement.findById(usuario.id) }
    }

    @Test
    fun saveUsuario() {
        every { usuarioRepositoryImplement.save(usuario) } returns usuario
        val res = usuariosController.save(usuario)
        assert(res == usuario)
        verify(exactly = 1) { usuarioRepositoryImplement.save(usuario) }
    }

    @Test
    fun deleteUsuario() {
        every { usuarioRepositoryImplement.delete(usuario) } returns true
        val res = usuariosController.delete(usuario)
        assert(res)
        verify(exactly = 1) { usuarioRepositoryImplement.delete(usuario) }
    }

    @Test
    fun deleteNoExiste() {
        every { usuarioRepositoryImplement.delete(usuario) } throws UsuarioException("Error: No encontrado usuario con id: ${usuario.id}")
        val res = assertThrows<UsuarioException> { usuariosController.delete(usuario) }
        assert(res.message == "Error: No encontrado usuario con id: ${usuario.id}")
        verify(exactly = 1) { usuarioRepositoryImplement.delete(usuario) }
    }

}