package io.etip.sdk.example

import io.etip.sdk.api.pokemon.config.Environment
import io.etip.sdk.api.pokemon.config.PokemonConfiguration
import io.etip.sdk.api.pokemon.di.ApiRegistry
import io.etip.sdk.api.pokemon.di.AppModule
import io.etip.sdk.api.pokemon.integration.PokemonApiService
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val config = PokemonConfiguration(
        baseUrl = "https://pokeapi.co/",
        environment = Environment.STAGE,
        enableLogging = true
    )
    AppModule.init(config)

    val pokemonDetail = ApiRegistry.getApi<PokemonApiService>().getPokemonByName("pikachu")
    println(pokemonDetail)
    
}
