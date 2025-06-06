package io.etip.sdk.api.pokemon.di

import io.etip.sdk.api.pokemon.config.PokemonConfiguration
import io.etip.sdk.api.pokemon.integration.PokemonApiService
import io.etip.sdk.api.pokemon.integration.impl.DefaultPokemonApiService
import io.etip.sdk.core.BaseHttpClient
import io.etip.sdk.core.HttpClientFactory

object AppModule {

    fun init(config: PokemonConfiguration) {
        val client = HttpClientFactory.create(config)
        val baseHttpClient = BaseHttpClient(client)
        val service: PokemonApiService = DefaultPokemonApiService(baseHttpClient)

        ApiRegistry.registerApi(service)
    }
    
}
