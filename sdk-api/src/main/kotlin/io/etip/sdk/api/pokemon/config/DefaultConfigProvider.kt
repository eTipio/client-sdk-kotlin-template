package io.etip.sdk.api.pokemon.config

object DefaultConfigProvider: ConfigProvider {
    override fun baseUrl(): String = ConfigManager.baseUrl()
}
