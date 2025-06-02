package io.etip.sdk.core

import io.etip.sdk.core.exception.SdkException
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun create(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, _ ->
                    throw when (cause) {
                        is ClientRequestException -> SdkException("Client error", cause)
                        is ServerResponseException -> SdkException("Server error", cause)
                        else -> SdkException("Unexpected error", cause)
                    }
                }
            }
        }
    }
}
