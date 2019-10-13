package com.appetiser.testapp.presenter.model

import kotlinx.serialization.Serializable

@Serializable
data class Track(
    val name: String,
    val imageUrlSmall: String,
    val imageUrlLarge: String,
    val price: Double,
    val currency: String,
    val shortDescription: String,
    val longDescription: String,
    val genre: String
)