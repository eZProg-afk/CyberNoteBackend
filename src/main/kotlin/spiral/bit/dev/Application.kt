package spiral.bit.dev

import io.ktor.server.application.*
import org.koin.ktor.ext.inject
import spiral.bit.dev.data.database.AppDatabase
import spiral.bit.dev.data.repositories.UserRepository
import spiral.bit.dev.jwtAuthentication.JwtService
import spiral.bit.dev.plugins.*

const val SERVER_VERSION = "/1.0"

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    AppDatabase.launch()
    installDI()
    val jwtService by inject<JwtService>()
    val userRepository by inject<UserRepository>()
    installAuthentication(jwtService, userRepository)
    configureRouting()
    configureSecurity()
    configureTemplating()
    configureSerialization()
    configureSockets()
}
