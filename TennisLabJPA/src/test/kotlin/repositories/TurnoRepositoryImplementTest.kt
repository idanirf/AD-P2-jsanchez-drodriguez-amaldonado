package repositories

import db.HibernateManager
import models.Turno
import models.Usuario
import models.enums.TipoUsuario
import org.junit.jupiter.api.*
import repositories.turno.TurnoRepositoryImplement
import repositories.usuario.UsuarioRepositoryImplement
import java.time.LocalDateTime
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TurnoRepositoryImplementTest{
    private val turnoRepositoryImplement = TurnoRepositoryImplement()
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

    private val turno = Turno(
        id = 0,
        uuid = UUID.randomUUID(),
        fechaInicio = LocalDateTime.of(2022, 10, 14, 9, 10),
        fechaFinal = LocalDateTime.of(2022, 11, 20, 10, 20),
        usuario = usuario
    )
    @AfterEach
    fun tearDown(){
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM Turnos")
            query.executeUpdate()
        }
    }
    @BeforeEach
    fun beforeEach(){
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM Turnos")
            query.executeUpdate()
        }
        HibernateManager.open()
        HibernateManager.close()
    }
    @Test
    fun findAll(){
        val res = turnoRepositoryImplement.findAll()
        assert(res.isEmpty())
    }
    @Test
    fun findById(){
        turnoRepositoryImplement.save(turno)
        val res = turnoRepositoryImplement.findById(turno.id)

        assert(res == turno)
    }
    @Test
    fun findByIdNoExiste(){
        val res = turnoRepositoryImplement.findById(-5)
        assert(res == null)
    }
    @Test
    fun saveInsert(){
        usuarioRepositoryImplement.save(usuario)
        val res = turnoRepositoryImplement.save(turno)

        assertAll(
            { Assertions.assertEquals(res.id, turno.id) },
            { Assertions.assertEquals(res.uuid, turno.uuid) },
            { Assertions.assertEquals(res.fechaInicio, turno.fechaInicio) },
            { Assertions.assertEquals(res.fechaFinal, turno.fechaFinal) },
            { Assertions.assertEquals(res.usuario, turno.usuario) }
        )
    }
    @Test
    fun delete() {
        usuarioRepositoryImplement.save(usuario)
        turnoRepositoryImplement.save(turno)

        val res = turnoRepositoryImplement.delete(turno.id)

        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = turnoRepositoryImplement.delete(turno.id)

        assert(!res)
    }
}