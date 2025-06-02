package io.etip.sdk.example

import io.etip.sdk.api.pokemon.config.PokemonClientConfig
import io.etip.sdk.api.pokemon.factory.PokemonSdkFactory
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val config = PokemonClientConfig(
        baseUrl = "https://pokeapi.co/",
        username = "",
        password = "",
        environment = PokemonClientConfig.Environment.PROD
    )

    val sdk = PokemonSdkFactory.create(config)

    runBlocking {
        val pikachu = sdk.getPokemonByName("pikachu")
        println("Pokemon: $pikachu")
    }
}
