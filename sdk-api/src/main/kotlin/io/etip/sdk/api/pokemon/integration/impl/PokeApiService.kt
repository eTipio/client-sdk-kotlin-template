package io.etip.sdk.api.pokemon.integration.impl

import io.etip.sdk.api.pokemon.config.PokemonConfiguration
import io.etip.sdk.api.pokemon.integration.PokemonService
import io.etip.sdk.api.pokemon.model.Pokemon
import io.etip.sdk.core.http.BaseHttpClient
import io.etip.sdk.core.http.HttpClientFactory

class PokeApiService(
    private val httpClient: BaseHttpClient,
) : PokemonService {
    override suspend fun getByName(name: String): Pokemon = httpClient.get("api/v2/pokemon/$name")

    override suspend fun getById(id: Int): Pokemon = httpClient.get("api/v2/pokemon/$id")

    companion object {
        fun create(config: PokemonConfiguration): PokeApiService {
            val client = HttpClientFactory.create(config)
            val baseHttpClient = BaseHttpClient(client)
            return PokeApiService(baseHttpClient)
        }
    }
}
