package spiral.bit.dev.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import spiral.bit.dev.base.extensions.generateSuccessMessageResponse
import spiral.bit.dev.base.extensions.errorHappened
import spiral.bit.dev.base.extensions.respondBadRequest
import spiral.bit.dev.base.extensions.respondOk
import spiral.bit.dev.data.models.User
import spiral.bit.dev.data.models.requests.LoginRequest
import spiral.bit.dev.data.models.requests.RegisterRequest
import spiral.bit.dev.data.repositories.UserRepository
import spiral.bit.dev.jwtAuthentication.JwtService

fun Route.userRoutes(
    userRepository: UserRepository,
    jwtService: JwtService,
    hashFunction: (String) -> String
) {
    post("users/register") {
        runCatching {
            call.receive<RegisterRequest>()
        }.onSuccess { registerRequestSucceed ->
            User(
                email = registerRequestSucceed.email,
                name = registerRequestSucceed.name,
                hashPassword = hashFunction(registerRequestSucceed.password)
            ).run {
                try {
                    userRepository.register(this)
                    val succeedServerResponse =
                        call.generateSuccessMessageResponse(
                            msg = "User successfully registered",
                            data = jwtService.generateToken(this)
                        )
                    call.respondOk(succeedServerResponse)
                } catch (e: Exception) {
                    val errorServerResponse = call.errorHappened(message = "This user already registered!")
                    call.respondBadRequest(errorServerResponse)
                }
            }
        }.onFailure {
            val errorServerResponse = call.errorHappened(message = "Missing some fields", throwable = it)
            call.respondBadRequest(errorServerResponse)
            return@post
        }
    }

    post("users/login") {
        runCatching {
            call.receive<LoginRequest>()
        }.onSuccess { loginRequestSucceed ->
            userRepository.getUserByEmail(loginRequestSucceed.email)?.let { user ->
                if (user.hashPassword == hashFunction(loginRequestSucceed.password)) {
                    val succeedServerResponse = call.generateSuccessMessageResponse(
                        msg = "User successfully logged in!",
                        data = jwtService.generateToken(user)
                    )
                    call.respondOk(succeedServerResponse)
                } else {
                    val errorServerResponse = call.errorHappened(message = "Password Incorrect")
                    call.respondBadRequest(errorServerResponse)
                    return@post
                }
            }
        }.onFailure {
            val errorServerResponse = call.errorHappened(message = "Missing some fields", throwable = it)
            call.respondBadRequest(errorServerResponse)
            return@post
        }
    }
}