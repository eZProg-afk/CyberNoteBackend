package spiral.bit.dev.plugins

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import spiral.bit.dev.di.authenticationModule
import spiral.bit.dev.di.repositoriesModule

fun Application.installDI() {
    install(Koin) {
        modules(repositoriesModule, authenticationModule)
    }
}