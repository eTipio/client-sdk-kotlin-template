package io.etip.sdk.examples.pokemon.di

import io.etip.sdk.api.pokemon.config.PokemonConfiguration
import io.etip.sdk.api.pokemon.integration.PokemonService
import io.etip.sdk.api.pokemon.integration.impl.PokeApiService
import io.etip.sdk.core.http.BaseHttpClient
import io.etip.sdk.core.http.HttpClientFactory
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

object AppModule {
    fun init(config: PokemonConfiguration) {
        val client = HttpClientFactory.create(config)
        val baseHttpClient = BaseHttpClient(client)
        val service: PokemonService = PokeApiService(baseHttpClient)

        startKoin {
            modules(
                module {
                    single<PokemonService> { service }
                },
            )
        }
    }
}
