package dev.coodie.api.global.security

import java.security.MessageDigest

private fun ByteArray.toHex() = joinToString(separator = "") { eachByte ->
    "%02x".format(eachByte)
}

fun sha256(origin: String): String {
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(origin.toByteArray())
    return digest.toHex()
}
