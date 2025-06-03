package io.etip.sdk.api.pokemon.di

import io.etip.sdk.api.pokemon.config.Environment
import io.etip.sdk.api.pokemon.config.PokemonConfiguration
import io.etip.sdk.api.pokemon.config.PokemonConfigurationFactory
import io.etip.sdk.api.pokemon.config.mock.MockConfigProvider
import io.etip.sdk.api.pokemon.integration.PokemonApiService
import io.etip.sdk.api.pokemon.integration.impl.DefaultPokemonApiService
import io.etip.sdk.core.BaseHttpClient
import io.etip.sdk.core.HttpClientFactory

object AppModule {

    fun init(environment: Environment) {
        val config = PokemonConfigurationFactory(MockConfigProvider).create()
        val pokemonConfig = PokemonConfiguration(
            baseUrl = config.baseUrl,
            environment = environment,
            enableLogging = true,
            timeoutMillis = 30_000
        )

        val client = HttpClientFactory.create(pokemonConfig)
        val baseHttpClient = BaseHttpClient(client)
        val service: PokemonApiService = DefaultPokemonApiService(baseHttpClient)

        ApiRegistry.registerApi(service)
    }

}
