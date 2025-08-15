package io.etip.sdk.examples.pokemon

import io.etip.sdk.api.pokemon.config.PokemonConfiguration
import io.etip.sdk.api.pokemon.integration.PokemonService
import io.etip.sdk.api.pokemon.integration.impl.PokeApiService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

object App : KoinComponent {
    suspend fun run() {
        val config = PokemonConfiguration(baseUrl = "https://pokeapi.co/")
        val pokeApiService: PokemonService = PokeApiService.create(config)

        // we just assume the service is used in many places, so we initialize it once
        // and inject it where needed. If not useful, you can just use the service directly
        // but Koin allows for better dependency management and testing.
        startKoin {
            modules(
                module {
                    single<PokemonService> { pokeApiService }
                },
            )
        }

        // Now we can use the service in our application
        val service: PokemonService by inject()
        val pokemon = service.getByName("pikachu")
        println("Found pokemon Name : ${pokemon.name}, ID : ${pokemon.id}, Height : ${pokemon.height}, Weight : ${pokemon.weight}")
    }
}
