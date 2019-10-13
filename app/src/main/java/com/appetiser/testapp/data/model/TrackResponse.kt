package com.appetiser.testapp.data.model

import com.appetiser.testapp.presenter.model.Track
import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class TrackResponse(
    val results: List<Result>
){
    /**
     * Mapper to Ui Model
     */
    fun toTracksData():List<Track>{
        val trackResults = mutableListOf<Track>()
        results.forEach {
            trackResults.add(it.toTrackData())
        }
        return trackResults
    }

}

@Serializable
data class Result(
    @Optional val trackName: String? = null,
    @Optional val artworkUrl60: String? = null,
    @Optional val artworkUrl100: String? = null,
    @Optional val trackPrice: Double? = null,
    @Optional val currency: String? = null,
    @Optional val primaryGenreName: String? = null,
    @Optional val shortDescription: String? = null,
    @Optional val longDescription: String? = null
){
    /**
     * Mapper to Ui Model
     */
    fun toTrackData():Track{
        return Track(
            name = trackName?:"Not Available",
            imageUrlLarge = artworkUrl100?:"",
            imageUrlSmall = artworkUrl60?:"",
            price = trackPrice?: 0.0,
            currency = currency?:"Not Available",
            shortDescription = shortDescription?:"Not Available",
            longDescription = longDescription?:"Not Available",
            genre = primaryGenreName?:"Not Available"
        )
    }
}