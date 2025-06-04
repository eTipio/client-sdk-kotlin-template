package io.etip.sdk.core

import io.etip.sdk.core.exception.SdkException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType

open class BaseHttpClient(val client: HttpClient) {

    suspend inline fun <reified T> get(
        path: String,
        queryParams: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap()
    ): T = request(HttpMethod.Get, path, headers, queryParams)

    suspend inline fun <reified T> post(
        path: String,
        body: Any,
        headers: Map<String, String> = emptyMap()
    ): T = request(HttpMethod.Post, path, headers, body = body)

    suspend inline fun <reified T> put(
        path: String,
        body: Any,
        headers: Map<String, String> = emptyMap()
    ): T = request(HttpMethod.Put, path, headers, body = body)

    suspend inline fun <reified T> delete(
        path: String,
        headers: Map<String, String> = emptyMap()
    ): T = request(HttpMethod.Delete, path, headers)

    suspend inline fun <reified T> request(
        method: HttpMethod,
        path: String,
        headers: Map<String, String> = emptyMap(),
        queryParams: Map<String, String> = emptyMap(),
        body: Any? = null
    ): T {
        return try {
            client.request(path) {
                this.method = method

                headers.forEach { (key, value) ->
                    header(key, value)
                }

                url {
                    queryParams.forEach { (key, value) ->
                        parameters.append(key, value)
                    }
                }

                if (body != null) {
                    contentType(ContentType.Application.Json)
                    setBody(body)
                }
            }.body()
        } catch (ex: Exception) {
            throw SdkException("${method.value} request failed: ${ex.message}", ex)
        }
    }

}
