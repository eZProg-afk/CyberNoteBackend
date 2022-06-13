package spiral.bit.dev.di

import org.koin.dsl.module
import spiral.bit.dev.data.repositories.UserRepository

val repositoriesModule = module {
    single { UserRepository() }
}