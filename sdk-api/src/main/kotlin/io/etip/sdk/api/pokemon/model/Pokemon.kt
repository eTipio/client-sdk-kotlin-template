package io.etip.sdk.api.pokemon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,

    @SerialName("base_experience")
    val baseExperience: Int
)
