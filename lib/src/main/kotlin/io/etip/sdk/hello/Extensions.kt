@file:JvmName("Extensions")
package io.etip.sdk.hello

inline fun <reified T> JsonDecoder.decode(json: String): T = decode(json, T::class.java)
