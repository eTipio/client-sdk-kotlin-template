package io.etip.sdk.core.http

import io.etip.sdk.core.exception.ClientException
import io.etip.sdk.core.exception.ServerException
import io.etip.sdk.core.exception.UnexpectedException
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun create(config: HttpClientConfig): HttpClient {
        return HttpClient(CIO) {
            install(HttpTimeout) {
                requestTimeoutMillis = config.timeoutMillis
                connectTimeoutMillis = config.timeoutMillis
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }

            if (config.enableLogging) {
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }

            defaultRequest {
                url(config.baseUrl)
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, _ ->
                    throw when (cause) {
                        is ClientRequestException -> ClientException(cause = cause)
                        is ServerResponseException -> ServerException(cause = cause)
                        else -> UnexpectedException(cause = cause)
                    }
                }
            }
        }
    }
}
