package repositories

import db.HibernateManager
import models.Usuario
import models.enums.TipoUsuario
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import repositories.usuario.UsuarioRepositoryImplement
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UsuarioRepositoryImplementTest {
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
    @AfterEach
    fun tearDown(){
        HibernateManager.transaction {
            val query= HibernateManager.manager.createNativeQuery("DELETE FROM Usuario")
            query.executeUpdate()
        }
    }
    @BeforeEach
    fun beforeEach(){
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM Usuario")
            query.executeUpdate()
        }
        HibernateManager.open()
        HibernateManager.close()
    }
    @Test
    fun findAll(){
        val res = usuarioRepositoryImplement.findAll()

        assert(res.isEmpty())
    }
    //Da fallo
    @Test
    fun findById(){
        usuarioRepositoryImplement.save(usuario)
        val res = usuarioRepositoryImplement.findById(usuario.id)
        assert(res == usuario)
    }
    @Test
    fun findByIdNoExiste(){
        val res = usuarioRepositoryImplement.findById(-5)
        assert(res==null)
    }
    //Da fallo
    @Test
    fun saveInsert(){
        val res = usuarioRepositoryImplement.save(usuario)

        assertAll(
            { assertEquals(res.id, usuario.id) },
            { assertEquals(res.uuid, usuario.uuid) },
            { assertEquals(res.nombre, usuario.nombre) },
            { assertEquals(res.apellido, usuario.apellido) },
            { assertEquals(res.correo, usuario.correo) },
            { assertEquals(res.password, usuario.password) },
            { assertEquals(res.tipoUsuario, usuario.tipoUsuario) }
        )
    }
    @Test
    fun delete(){
        usuarioRepositoryImplement.save(usuario)
        val res = usuarioRepositoryImplement.delete(usuario.id)
        assert(res)
    }
    @Test
    fun deleteNoExiste(){
        val res = usuarioRepositoryImplement.delete(usuario.id)
        assert(!res)
    }
}