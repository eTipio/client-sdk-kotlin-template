package io.etip.sdk.api.pokemon.config

import io.github.cdimascio.dotenv.Dotenv

object ConfigManager {
    private val dotenv =
        Dotenv
            .configure()
            .ignoreIfMissing()
            .load()

    private val pokemon = Pokemon.Companion.fromEnv()

    private val context by lazy { pokemon.getContext() }
    private val secrets by lazy { pokemon.getSecrets() }
    private val configs by lazy { pokemon.getConfigs() }

    fun baseUrl(): String = configs[PokemonConfigKeys.BASE_URL].orEmpty()

    fun env(key: String): String? = dotenv[key]
}
