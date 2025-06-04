package io.etip.sdk.api.pokemon.config

import io.etip.sdk.core.ApiConfig

class PokemonConfiguration(
    baseUrl: String,
    environment: Environment,
    enableLogging: Boolean = false,
    timeoutMillis: Long = 30_000
): ApiConfig(baseUrl, enableLogging, timeoutMillis)

enum class Environment {
    PROD, STAGE
}
