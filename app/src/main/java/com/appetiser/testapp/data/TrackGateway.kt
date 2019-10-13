package com.appetiser.testapp.data

import com.appetiser.testapp.presenter.model.Track
import io.reactivex.Completable
import io.reactivex.Single

interface TrackGateway {

    /**
     * Retrieves list of tracks from api if there is no available tracks in memory
     * @param term Query parameter term
     * @param country Query parameter country
     * @param media Query parameter media type
     * @param invalidateCache Indicator whether to reload data from api
     * @return RxSingle of list of track
     */
    fun getTracks(term: String,country: String, media: String,invalidateCache: Boolean) : Single<List<Track>>

    /**
     * Retrieves the track cached
     * @return RxSingle of Track
     */
    fun getTrack(): Single<Track>

    /**
     * Caches the track
     * @param track Track to cache
     * @return RxCompletable
     */
    fun setTrack(track: Track?): Completable

    /**
     * Logs the current date
     * @param date date formatted string
     * @return RxCompletable
     */
    fun setLastLogDate(date: String): Completable

    /**
     * Retrieves the last cached date
     * @return RxSingle of string
     */
    fun getLastLogDate(): Single<String>

}