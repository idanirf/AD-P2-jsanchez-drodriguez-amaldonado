package services

import com.google.common.hash.Hashing
import java.nio.charset.StandardCharsets

/**
 * Encriptar
 *
 * @param contra
 * @return
 */
fun encriptar(contra: String): String {
    return Hashing.sha256()
        .hashString(contra, StandardCharsets.UTF_8)
        .toString()
}