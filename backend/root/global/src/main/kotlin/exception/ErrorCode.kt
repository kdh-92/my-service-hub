package exception

enum class ErrorCode(val code: String, val message: String, val status: Int) {
    // 인증 관련 에러
    AUTHENTICATION_FAILED("AUTH001", "Authentication failed", 401),
    ACCESS_DENIED("AUTH002", "Access denied", 403),

    // 사용자 관련 에러
    USER_NOT_FOUND("USER001", "User not found", 404),
    USER_ALREADY_EXISTS("USER002", "User already exists", 409),

    // 마켓 관련 에러
    PRODUCT_NOT_FOUND("MARKET001", "Product not found", 404),
    INVALID_PRODUCT_ID("MARKET002", "Invalid product ID", 400),

    // 유효하지 않은 요청 관련 에러
    INVALID_REQUEST("REQ001", "Invalid request parameters", 400),

    // 서버 에러
    INTERNAL_SERVER_ERROR("SYS001", "Internal server error", 500);
}