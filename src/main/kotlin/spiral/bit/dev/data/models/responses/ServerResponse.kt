package spiral.bit.dev.data.models.responses

import kotlinx.serialization.Serializable

interface ServerResponse

@Serializable
data class ErrorServerResponse(
    val localizedMessage: String,
    val causeMessage: String,
    val stackTrace: String
) : ServerResponse

@Serializable
data class SuccessMessageResponse(
    val msg: String,
    val data: String
) : ServerResponse

