package io.etip.sdk.examples.pokemon.di

import io.etip.sdk.api.pokemon.config.PokemonConfiguration
import io.etip.sdk.api.pokemon.integration.PokemonService
import io.etip.sdk.api.pokemon.integration.impl.PokeApiService
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

object AppModule {
    fun init(config: PokemonConfiguration) {
        val service: PokemonService = PokeApiService.create(config)
        startKoin {
            modules(
                module {
                    single<PokemonService> { service }
                },
            )
        }
    }
}
