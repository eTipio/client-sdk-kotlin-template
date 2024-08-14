package io.etip.sdk.hello.greetings

import java.time.LocalDateTime

@JvmRecord
data class GetGreetingResponse(val message: String, val createdAt: LocalDateTime)
