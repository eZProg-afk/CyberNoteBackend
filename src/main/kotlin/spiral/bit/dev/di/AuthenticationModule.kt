package spiral.bit.dev.di

import org.koin.dsl.module
import spiral.bit.dev.jwtAuthentication.JwtService

val authenticationModule = module {
    single { JwtService() }
}