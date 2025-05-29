package io.etip.sdk.api.pokemon.config

data class PokemonClientConfig(
    val baseUrl: String,
    val username: String,
    val password: String,
    val environment: Environment
) {
    enum class Environment {
        PROD,
        STAGING
    }
}
