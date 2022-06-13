package spiral.bit.dev.base.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction

suspend fun <R> ioTransaction(block: () -> R) = withContext(Dispatchers.IO) {
    transaction { block() }
}