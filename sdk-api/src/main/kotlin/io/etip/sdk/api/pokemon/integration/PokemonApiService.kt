package io.etip.sdk.api.pokemon.integration

import io.etip.sdk.api.pokemon.model.PokemonDetail

interface PokemonApiService {
    suspend fun getPokemonByName(name: String): PokemonDetail
    suspend fun getPokemonById(id: Int): PokemonDetail
}
