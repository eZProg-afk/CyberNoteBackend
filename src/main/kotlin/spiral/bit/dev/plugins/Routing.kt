package spiral.bit.dev.plugins

import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import spiral.bit.dev.data.repositories.UserRepository
import spiral.bit.dev.jwtAuthentication.JwtService
import spiral.bit.dev.jwtAuthentication.hash
import spiral.bit.dev.routes.userRoutes

fun Application.configureRouting() {
    install(Locations) {
    }

    routing {
        val jwtService by inject<JwtService>()
        val userRepository by inject<UserRepository>()
        val hashFunction: (String) -> String = { s: String -> hash(s) }

        userRoutes(userRepository, jwtService) { hash ->
            hashFunction(hash)
        }

        get("/") {
            call.respond("ALL OK!")
        }
    }
}