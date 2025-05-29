package io.etip.sdk.core

import io.etip.sdk.core.exception.SdkException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodedPath

open class BaseHttpClient(val client: HttpClient, val baseUrl: String) {

    suspend inline fun <reified T> get(
        path: String,
        queryParams: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap()
    ): T {
        return try {
            client.get(baseUrl + path) {
                headers.forEach { (key, value) ->
                    header(key, value)
                }
                url {
                    queryParams.forEach { (key, value) ->
                        parameters.append(key, value)
                    }
                }
            }.body()
        } catch (ex: Exception) {
            throw SdkException("GET request failed: ${ex.message}", ex)
        }
    }

    suspend inline fun <reified T> post(
        path: String,
        body: Any,
        headers: Map<String, String> = emptyMap()
    ): T {
        return try {
            client.post(baseUrl + path) {
                url { encodedPath = path }
                contentType(ContentType.Application.Json)
                setBody(body)
                headers.forEach { (key, value) ->
                    header(key, value)
                }
            }.body()
        } catch (ex: Exception) {
            throw SdkException("POST request failed: ${ex.message}", ex)
        }
    }

}
