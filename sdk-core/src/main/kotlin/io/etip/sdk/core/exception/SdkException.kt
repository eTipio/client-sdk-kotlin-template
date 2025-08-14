package io.etip.sdk.core.exception

open class SdkException(message: String, cause: Throwable? = null): Exception(message, cause)
class ClientException(message: String = "A Client error occured", cause: Throwable? = null): SdkException(message, cause)
class ServerException(message: String = "A Server error occured", cause: Throwable? = null): SdkException(message, cause)
class UnexpectedException(message: String = " An unexpected error occured", cause: Throwable? = null): SdkException(message, cause)
