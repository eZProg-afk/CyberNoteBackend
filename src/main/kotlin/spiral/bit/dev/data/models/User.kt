package spiral.bit.dev.data.models

import io.ktor.server.auth.*

data class User(
    val email: String,
    val name: String,
    val hashPassword: String
) : Principal
