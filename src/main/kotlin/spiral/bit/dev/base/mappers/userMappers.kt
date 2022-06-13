package spiral.bit.dev.base.mappers

import org.jetbrains.exposed.sql.ResultRow
import spiral.bit.dev.data.database.tables.UserTable
import spiral.bit.dev.data.models.User

fun ResultRow?.mapToUser(): User? = this?.let {
    User(
        email = this[UserTable.email],
        name = this[UserTable.name],
        hashPassword = this[UserTable.hashPassword]
    )
} ?: run { null }