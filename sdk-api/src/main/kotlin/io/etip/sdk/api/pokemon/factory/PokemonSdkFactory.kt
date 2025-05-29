package io.etip.sdk.api.pokemon.factory

import io.etip.sdk.api.pokemon.config.PokemonClientConfig
import io.etip.sdk.api.pokemon.integration.PokemonApiService
import io.etip.sdk.api.pokemon.integration.impl.DefaultPokemonApiService
import io.etip.sdk.core.BaseHttpClient
import io.etip.sdk.core.HttpClientFactory

object PokemonSdkFactory {
    fun create(config: PokemonClientConfig): PokemonApiService {
        val client = HttpClientFactory().create()
        val baseHttpClient = BaseHttpClient(client, config.baseUrl)
        return DefaultPokemonApiService(config, baseHttpClient)
    }
}
