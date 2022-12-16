package repositories

import models.Usuario
import models.enums.TipoUsuario
import org.junit.jupiter.api.TestInstance
import repositories.usuario.UsuarioRepositoryImplement
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UsusarioRepositoryTest {
    private val usuarioRepositoryImplement = UsuarioRepositoryImplement()

    private val usuario = Usuario(
        id = 0,
        uuid = UUID.randomUUID(),
        nombre = "Alfonso",
        apellido = "Cabello",
        correo = "alfonso@cabello.com",
        password = "1234",
        tipoUsuario = TipoUsuario.USUARIO
    )
}