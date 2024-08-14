package io.etip.sdk.hello

import okhttp3.OkHttpClient

@JvmRecord
data class HelloClientConfig(
    val baseUri: String,
    val secretKey: String,
    val httpClient: OkHttpClient? = null,
    val codecs: JsonCodec? = null
)
