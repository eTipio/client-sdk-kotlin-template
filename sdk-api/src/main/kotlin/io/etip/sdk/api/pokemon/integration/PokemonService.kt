package io.etip.sdk.api.pokemon.integration

import io.etip.sdk.api.pokemon.model.Pokemon

interface PokemonService {
    suspend fun getByName(name: String): Pokemon

    suspend fun getById(id: Int): Pokemon
}
