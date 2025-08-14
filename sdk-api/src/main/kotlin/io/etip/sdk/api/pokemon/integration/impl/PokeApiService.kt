package io.etip.sdk.api.pokemon.integration.impl

import io.etip.sdk.api.pokemon.integration.PokemonService
import io.etip.sdk.api.pokemon.model.Pokemon
import io.etip.sdk.core.http.BaseHttpClient

class PokeApiService(
    private val httpClient: BaseHttpClient,
) : PokemonService {
    override suspend fun getByName(name: String): Pokemon {
        return httpClient.get("api/v2/pokemon/$name")
    }

    override suspend fun getById(id: Int): Pokemon {
        return httpClient.get("api/v2/pokemon/$id")
    }
}
