package io.etip.sdk.examples.pokemon

import io.etip.sdk.api.pokemon.config.PokemonConfiguration
import io.etip.sdk.api.pokemon.di.AppModule
import io.etip.sdk.api.pokemon.integration.PokemonService
import kotlinx.coroutines.runBlocking
import org.koin.java.KoinJavaComponent.inject

fun main() = runBlocking {

    val config = PokemonConfiguration(baseUrl = "https://pokeapi.co/")
    AppModule.init(config)

    val service = inject<PokemonService>(PokemonService::class.java).value
    val pokemon = service.getByName("pikachu")

    println("Found pokemon Name : ${pokemon.name}, ID : ${pokemon.id}, Height : ${pokemon.height}, Weight : ${pokemon.weight}")
    
}
