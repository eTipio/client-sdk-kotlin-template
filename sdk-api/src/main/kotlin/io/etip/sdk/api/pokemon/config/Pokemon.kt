package io.etip.sdk.api.pokemon.config

import io.github.cdimascio.dotenv.Dotenv
import kotlin.collections.set

class Pokemon {
    private val configMap = mutableMapOf<String, String>()
    private val secretsMap = mutableMapOf<String, String>()
    private val contextMap = mutableMapOf<String, String>()

    fun getConfigs(): Map<String, String> = configMap.toMap()
    fun getSecrets(): Map<String, String> = secretsMap.toMap()
    fun getContext(): Map<String, String> = contextMap.toMap()

    companion object {
        fun fromEnv(): Pokemon {
            val dotenv = Dotenv.configure().ignoreIfMissing().load()

            val pokemon = Pokemon()

            dotenv.entries().forEach { entry ->
                when {
                    entry.key.startsWith("POK_CFG_") -> pokemon.configMap[entry.key.substringAfter("POK_CFG_").toCamelCase()] = entry.value
                    entry.key.startsWith("POK_SEC_") -> pokemon.secretsMap[entry.key.substringAfter("POK_SEC_").toCamelCase()] = entry.value
                    entry.key.startsWith("POK_CTX_") -> pokemon.contextMap[entry.key.substringAfter("POK_CTX_").toCamelCase()] = entry.value
                }
            }
            println("Pokemon configMap: ${pokemon.getSecrets()}")
            return pokemon
        }

        private fun String.toCamelCase(): String {
            return this.lowercase().split("_").joinToString("") { it.replaceFirstChar { char -> char.uppercaseChar() } }.replaceFirstChar { it.lowercase() }
        }
    }

}
