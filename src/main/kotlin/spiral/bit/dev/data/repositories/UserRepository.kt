package spiral.bit.dev.data.repositories

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import spiral.bit.dev.base.mappers.mapToUser
import spiral.bit.dev.base.utils.ioTransaction
import spiral.bit.dev.data.database.tables.UserTable
import spiral.bit.dev.data.models.User

class UserRepository {

    suspend fun register(user: User) = ioTransaction {
        UserTable.insert { table ->
            table[email] = user.email
            table[name] = user.name
            table[hashPassword] = user.hashPassword
        }
    }

    suspend fun getUserByEmail(email: String) = ioTransaction {
        UserTable.select { UserTable.email.eq(email) }.singleOrNull().mapToUser()
    }
}