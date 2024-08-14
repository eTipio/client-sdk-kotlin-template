package io.etip.sdk.hello.greetings.impl

import io.etip.sdk.hello.HelloClient
import io.etip.sdk.hello.greetings.GetGreetingRequest
import io.etip.sdk.hello.greetings.GetGreetingResponse
import io.etip.sdk.hello.greetings.GreetingFailedException
import io.etip.sdk.hello.greetings.GreetingsApi
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class GreetingsApiImpl(private val client: HelloClient) : GreetingsApi {
    override fun getGreeting(getGreetingRequest: GetGreetingRequest): GetGreetingResponse {
        val requestUrl = client.baseUri() + "/greetings?name=" + getGreetingRequest.name
        val response: Response = try {
            client.httpClient()
                .newCall(Request.Builder().get().url(requestUrl).build())
                .execute()
        } catch (e: IOException) {
            throw GreetingFailedException(e.message)
        }

        if (response.code != 200) {
            throw GreetingFailedException("Failed to get greeting: ")
        }

        try {
            return client.codecs().decoder
                .decode(
                    response.body.string(),
                    GetGreetingResponse::class.java
                )
        } catch (e: IOException) {
            throw GreetingFailedException(e.message)
        }
    }
}
