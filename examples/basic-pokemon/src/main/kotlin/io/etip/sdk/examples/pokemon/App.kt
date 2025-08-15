package io.etip.sdk.examples.pokemon

import io.etip.sdk.api.pokemon.config.PokemonConfiguration
import io.etip.sdk.examples.pokemon.di.AppModule
import io.etip.sdk.api.pokemon.integration.PokemonService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object App : KoinComponent {
    suspend fun run() {
        val config = PokemonConfiguration(baseUrl = "https://pokeapi.co/")
        // assume service is used in many places, so we initialize it once
        // and inject it where needed
        AppModule.init(config)

        val service: PokemonService by inject()
        val pokemon = service.getByName("pikachu")

        println("Found pokemon Name : ${pokemon.name}, ID : ${pokemon.id}, Height : ${pokemon.height}, Weight : ${pokemon.weight}")
    }
}
