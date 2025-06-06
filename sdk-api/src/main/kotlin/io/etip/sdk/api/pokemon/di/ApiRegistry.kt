package io.etip.sdk.api.pokemon.di

import kotlin.collections.set
import kotlin.jvm.java

/**
 * `ApiRegistry` is a simple service locator or dependency registry that manages
 * a collection of API instances. It provides a way to register and retrieve
 * instances of different API interfaces throughout the application.
 *
 * This class uses a `mutableMapOf` to store APIs, where the key is the class
 * type and the value is the registered instance.
 */
object ApiRegistry {
    private val services = mutableMapOf<Class<*>, Any>()

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getApi(clazz: Class<T>): T =
        services[clazz] as? T
            ?: error("No API registered for class: ${clazz.simpleName}")

    fun <T : Any> registerApi(
        clazz: Class<T>,
        instance: T,
    ) {
        services[clazz] = instance
    }

    inline fun <reified T : Any> getApi(): T = getApi(T::class.java)

    inline fun <reified T : Any> registerApi(instance: T) = registerApi(T::class.java, instance)
}
