package io.etip.sdk.api.pokemon.config

import io.etip.sdk.api.pokemon.integration.exception.PokemonConfigurationException

class PokemonConfigurationFactory(
    private val configProvider: ConfigProvider,
) {
    fun create(): PokemonProperties {
        val baseUrl = configProvider.baseUrl()

        if (baseUrl.isBlank()) {
            throw PokemonConfigurationException("Pokemon configuration is incomplete")
        }

        return PokemonProperties(baseUrl = baseUrl)
    }
}
