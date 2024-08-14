package io.etip.sdk.hello.greetings

interface GreetingsApi {
    fun getGreeting(getGreetingRequest: GetGreetingRequest): GetGreetingResponse
}
