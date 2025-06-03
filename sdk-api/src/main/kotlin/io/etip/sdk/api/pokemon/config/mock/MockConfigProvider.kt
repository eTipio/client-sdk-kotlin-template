package io.etip.sdk.api.pokemon.config.mock

import io.etip.sdk.api.pokemon.config.ConfigProvider

object MockConfigProvider: ConfigProvider {
    override fun baseUrl(): String = "https://pokeapi.co/"
}
