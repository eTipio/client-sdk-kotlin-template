package io.etip.sdk.api.pokemon.config

import io.etip.sdk.core.http.HttpClientConfig

class PokemonConfiguration(
    baseUrl: String,
    enableLogging: Boolean = true,
    timeoutMillis: Long = 30_000,
) : HttpClientConfig(baseUrl, enableLogging, timeoutMillis)
