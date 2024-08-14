package io.etip.sdk.hello.greetings

@JvmRecord
data class GetGreetingRequest(val name: String) {
    // add validation to the constructor
    init {
        requireNotNull(name) { "name cannot be null" }
    }
}
