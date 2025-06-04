package io.etip.sdk.core

open class ApiConfig(
    val baseUrl: String,
    val enableLogging: Boolean = false,
    val timeoutMillis: Long = 30_000
)
