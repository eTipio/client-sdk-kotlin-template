package io.etip.sdk.api.pokemon.integration

import io.etip.sdk.core.http.Route

sealed class PokemonRoute : Route() {
    data class GetByName(
        val name: String,
    ) : PokemonRoute() {
        override val path = "api/v2/pokemon/$name"
    }

    data class GetById(
        val id: Int,
    ) : PokemonRoute() {
        override val path = "api/v2/pokemon/$id"
    }
}
