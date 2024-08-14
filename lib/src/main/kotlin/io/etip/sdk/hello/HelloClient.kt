package io.etip.sdk.hello

import com.fasterxml.jackson.databind.ObjectMapper
import io.etip.sdk.hello.greetings.GreetingsApi
import io.etip.sdk.hello.greetings.impl.GreetingsApiImpl
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.Route

 class HelloClient(private val config: HelloClientConfig) {
    private var httpClient: OkHttpClient
    private var codecs: JsonCodec
    private var baseUri: String = config.baseUri

    init {
        if (config.httpClient == null) {
            val httpClientBuilder = OkHttpClient().newBuilder()
            httpClientBuilder
                .authenticator { _: Route?, response: Response ->
                    response.request.newBuilder()
                        .header(
                            "Authorization",
                            config.secretKey
                        )
                        .build()
                }

            this.httpClient = httpClientBuilder.build()
        } else {
            this.httpClient = config.httpClient
        }

        if (config.codecs == null) {
            val objectMapper = ObjectMapper()
            this.codecs = JsonCodec(
                encoder = JsonEncoder { objectMapper.writeValueAsString(it) },
                decoder = object: JsonDecoder {
                    override fun <T : Any> decode(json: String, clazz: Class<T>): T {
                        return objectMapper.readValue(json, clazz)
                    }
                }
            )
        } else {
            this.codecs = config.codecs
        }
    }

    fun greetings(): GreetingsApi = GreetingsApiImpl(this)

    fun baseUri(): String = this.baseUri
    fun httpClient(): OkHttpClient = this.httpClient
    fun codecs(): JsonCodec = this.codecs
}
