package controller

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.mockk.every
import models.Usuario
import models.enums.TipoUsuario
import org.junit.jupiter.api.Test
import repositories.usuario.UsuarioRepositoryImplement
import java.util.*


internal class UsuarioControllerTest {
    @MockK
    lateinit var usuarioRepositoryImplement: UsuarioRepositoryImplement
    @InjectMockKs
    lateinit var usuarioController: UsuarioController

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
    fun findAllUsuarios(){
        every{usuarioRepositoryImplement.findAll()} returns listOf(usuario)
        val res = usuarioController.findAll()
        assert(res == listOf(usuario))
        verify(exactly = 1) {usuarioRepositoryImplement.findAll()  }
    }
    @Test
    fun findByIdUsuario(){
        every { usuarioRepositoryImplement.findById(usuario.id) }returns usuario
        val res = usuarioController.findById(usuario.id)
        assert(res==usuario)
        verify(exactly = 1) {usuarioRepositoryImplement.findById(usuario.id)  }
    }
    @Test
    fun findByIdNoExisteUsuario(){
        every { usuarioRepositoryImplement.findById(usuario.id) }returns null
        val res = usuarioController.findById(usuario.id)
        assert(res == null)
        verify(exactly = 1) {usuarioRepositoryImplement.findById(usuario.id)  }
    }
    @Test
    fun saveUsuario(){
        every { usuarioRepositoryImplement.save(usuario) }returns usuario
        val res = usuarioController.save(usuario)
        assert(res == usuario)
        verify(exactly = 1) {usuarioRepositoryImplement.save(usuario)  }
    }
    @Test
    fun deleteUsuario(){
        every { usuarioRepositoryImplement.delete(usuario) }returns true
        val res = usuarioController.delete(usuario)
        assert(res)
        verify(exactly = 1) {usuarioRepositoryImplement.delete(usuario)  }
    }
    @Test
    fun deleteNoExisteUsuario(){
        every { usuarioRepositoryImplement.delete(usuario) }returns false
        val res = usuarioController.delete(usuario)
        assert(!res)
        verify(exactly = 1) {usuarioRepositoryImplement.delete(usuario)  }
    }
}