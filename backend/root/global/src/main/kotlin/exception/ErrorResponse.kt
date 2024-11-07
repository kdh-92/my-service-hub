package exception

data class ErrorResponse(
    val code: String,
    val message: String,
    val status: Int,
) {
    companion object {
        fun from(errorCode: ErrorCode): ErrorResponse {
            return ErrorResponse(
                code = errorCode.code,
                message = errorCode.message,
                status = errorCode.status,
            )
        }
    }
}