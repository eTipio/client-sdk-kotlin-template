package io.etip.sdk.api.pokemon.integration.impl

import io.etip.sdk.api.pokemon.integration.PokemonApiService
import io.etip.sdk.api.pokemon.integration.PokemonRoute
import io.etip.sdk.api.pokemon.model.PokemonDetail
import io.etip.sdk.core.BaseHttpClient

class DefaultPokemonApiService(
    private val httpClient: BaseHttpClient,
) : PokemonApiService {
    override suspend fun getPokemonByName(name: String): PokemonDetail {
        val url = PokemonRoute.GetByName(name).path
        return httpClient.get(url)
    }

    override suspend fun getPokemonById(id: Int): PokemonDetail {
        val url = PokemonRoute.GetById(id).path
        return httpClient.get(url)
    }
}
