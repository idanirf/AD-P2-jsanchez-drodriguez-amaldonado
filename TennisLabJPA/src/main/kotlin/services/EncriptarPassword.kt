/**
 * @author Daniel Rodriguez
 * @author Jorge Sánchez
 * @author Alfredo Maldonado
 */
package services

import com.google.common.hash.Hashing
import java.nio.charset.StandardCharsets

class EncriptarPassword {
    fun encriptar(contra: String): String {
        return Hashing.sha256()
            .hashString(contra, StandardCharsets.UTF_8)
            .toString()
    }
}
