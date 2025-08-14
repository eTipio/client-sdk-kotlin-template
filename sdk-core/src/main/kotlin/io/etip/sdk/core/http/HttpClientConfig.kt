package io.etip.sdk.core.http

open class HttpClientConfig(
    val baseUrl: String,
    val enableLogging: Boolean = false,
    val timeoutMillis: Long = 30_000
)
