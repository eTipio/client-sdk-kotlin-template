package io.etip.sdk.api.pokemon.integration.exception

class PokemonConfigurationException(
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)
