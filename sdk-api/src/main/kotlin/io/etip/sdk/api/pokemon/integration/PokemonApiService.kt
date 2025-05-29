package io.etip.sdk.api.pokemon.integration

import io.etip.sdk.api.pokemon.model.Pokemon

interface PokemonApiService {
    suspend fun getPokemonByName(name: String): Pokemon
    suspend fun getPokemonById(id: Int): Pokemon
}
