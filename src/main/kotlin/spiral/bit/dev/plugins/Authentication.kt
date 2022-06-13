package spiral.bit.dev.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import spiral.bit.dev.data.repositories.UserRepository
import spiral.bit.dev.jwtAuthentication.JwtService

fun Application.installAuthentication(jwtService: JwtService, userRepository: UserRepository) {
    install(Authentication) {
        jwt("jwt") {
            verifier(jwtService.verifier)
            realm = "Cybernote Server"
            validate {
                val payload = it.payload
                val email = payload.getClaim("email").asString()
                val user = userRepository.getUserByEmail(email)
                user
            }
        }
    }
}