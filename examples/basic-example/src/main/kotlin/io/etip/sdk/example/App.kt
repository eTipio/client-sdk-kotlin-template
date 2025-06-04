package io.etip.sdk.example

import io.etip.sdk.api.pokemon.config.Environment
import io.etip.sdk.api.pokemon.config.Pokemon
import io.etip.sdk.api.pokemon.di.ApiRegistry
import io.etip.sdk.api.pokemon.di.AppModule
import io.etip.sdk.api.pokemon.integration.PokemonApiService
import io.github.cdimascio.dotenv.Dotenv
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

fun main() = runBlocking {
    val logger = LoggerFactory.getLogger("Pokemon")
    val dotenv = Dotenv.configure().ignoreIfMissing().load()

    val pokemon = Pokemon.fromEnv()

    val context = pokemon.getContext()
    val secrets = pokemon.getSecrets()
    val configs = pokemon.getConfigs()

    val runMode = context["runMode"]

    logger.info("Run mode: $runMode")

    logger.info("------------------------CONFIG------------------------")
    logger.info(configs.toString())
    logger.info("------------------------SECRET------------------------")
    logger.info(secrets.toString())
    logger.info("------------------------CONTEXT------------------------")
    logger.info(context.toString())
    logger.info("------------------------ENV----------------------------")
    logger.info(dotenv.entries().toString())
    AppModule.init(Environment.STAGE)

    val pokemonDetail = ApiRegistry.getApi<PokemonApiService>().getPokemonByName("pikachu")
    //val pokemonName = ApiRegistry.getApi<PokemonApiService>().getPokemonByName("pikachu")
    println(pokemonDetail)
}
