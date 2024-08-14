package io.etip.sdk.hello

@JvmRecord
data class JsonCodec(
    val encoder: JsonEncoder,
    val decoder: JsonDecoder
)
