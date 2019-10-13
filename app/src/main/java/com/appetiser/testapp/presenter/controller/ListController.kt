package com.appetiser.testapp.presenter.controller

import com.airbnb.epoxy.EpoxyController
import com.appetiser.testapp.presenter.item.dateItem
import com.appetiser.testapp.presenter.item.errorItem
import com.appetiser.testapp.presenter.item.loadingItem
import com.appetiser.testapp.presenter.item.trackItem
import com.appetiser.testapp.presenter.model.Track

class ListController constructor(
    private val callback: Callback
) : EpoxyController() {

    private var tracks = listOf<Track>()
    private var isLoading = false
    private var error = ""
    private var date = ""

    override fun buildModels() {

        if (date.isNotEmpty()){
            dateItem {
                id("DATE_ITEM")
                date("Last visit: $date")
            }
        }

        if (error.isNotEmpty()){
            errorItem {
                id("ERROR_ITEM")
                error(error)
                clickListener { _, _, _, _ ->
                    callback.onReloadList()
                }
            }
        }

        tracks.forEachIndexed { index, track ->
            trackItem {
                id("TRACK_ITEM$index")
                name(track.name)
                price("${track.price} ${track.currency}")
                genre(track.genre)
                imageUrl(track.imageUrlLarge)
                clickListener { _, _, _, _ ->
                    callback.onTrackClicked(track)
                }

            }
        }

        if (isLoading){
            loadingItem {
                id("LOADING_ITEM")
            }
        }
    }

    fun setTracks(tracks: List<Track>){
        this.tracks = tracks
        requestModelBuild()
    }

    fun setLoading(isLoading: Boolean){
        this.isLoading = isLoading
        requestModelBuild()
    }

    fun setError(error: String){
        this.error = error
        requestModelBuild()
    }

    fun setDate(date: String){
        this.date = date
        requestModelBuild()
    }

    interface  Callback {
        fun onTrackClicked(track: Track)
        fun onReloadList()
    }
}