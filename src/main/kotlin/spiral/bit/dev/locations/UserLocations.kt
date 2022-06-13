package spiral.bit.dev.locations

import io.ktor.server.locations.*
import kotlinx.serialization.Serializable
import spiral.bit.dev.SERVER_VERSION

const val USERS = "$SERVER_VERSION/users"
const val REGISTER_USER = "/"
const val LOGIN_USER = "$USERS/login"

@Location(REGISTER_USER)
@Serializable
class RegisterUser

@Location(LOGIN_USER)
class LoginUser
