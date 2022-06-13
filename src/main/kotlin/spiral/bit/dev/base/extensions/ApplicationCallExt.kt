package spiral.bit.dev.base.extensions

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import spiral.bit.dev.data.models.responses.ErrorServerResponse
import spiral.bit.dev.data.models.responses.SuccessMessageResponse

suspend fun ApplicationCall.errorHappened(
    message: String,
    throwable: Throwable? = null
) = ErrorServerResponse(
    localizedMessage = message,
    causeMessage = (throwable?.cause ?: "Cause empty") as String,
    stackTrace = throwable?.stackTrace.toString()
)

suspend fun ApplicationCall.generateSuccessMessageResponse(msg: String, data: String) =
    SuccessMessageResponse(msg = msg, data = data)

suspend fun ApplicationCall.respondBadRequest(
    errorServerResponse: ErrorServerResponse
) {
    respond(HttpStatusCode.BadRequest, errorServerResponse)
}

suspend fun ApplicationCall.respondOk(successMessageResponse: SuccessMessageResponse) {
    respond(HttpStatusCode.OK, successMessageResponse)
}