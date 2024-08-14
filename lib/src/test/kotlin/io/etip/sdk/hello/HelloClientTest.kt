package io.etip.sdk.hello

import okhttp3.OkHttpClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class HelloClientTest {

    @Test
    fun clientWithDefaultHttpClient() {
        val config = HelloClientConfig(
            baseUri = "http://localhost:8080",
            secretKey = "secretKey",
        )
        val helloClient = HelloClient(config)
        assertNotNull(helloClient)
        assertNotNull(helloClient.httpClient())
        assertNotNull(helloClient.codecs())
    }

    @Test
    fun clientWithCustomHttpClient() {
        val config = HelloClientConfig(
            baseUri = "http://localhost:8080",
            secretKey = "secretKey",
            httpClient = OkHttpClient.Builder().build()
        )
        val helloClient = HelloClient(config)
        assertNotNull(helloClient)
        assertEquals(config.httpClient, helloClient.httpClient())
    }
}