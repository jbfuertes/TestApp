package com.appetiser.testapp.data

import com.appetiser.testapp.presenter.model.Track
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

class TrackDataGateway
@Inject
constructor(private val trackService: TrackService,
            private val preferenceHelper: PreferenceHelper) : TrackGateway {

    private val tracks = mutableListOf<Track>()

    override fun getTracks(
        term: String,
        country: String,
        media: String,
        invalidateCache: Boolean
    ): Single<List<Track>> {
        return Single.defer {
            if (tracks.isNotEmpty() && invalidateCache.not()){
                Single.just(tracks)
            }else {
                trackService.getTracks(term,country,media)
                    .doAfterSuccess { Timber.d(it.results.size.toString()) }
                    .map { it.toTracksData() }
                    .doAfterSuccess {
                        tracks.clear()
                        tracks.addAll(it)
                    }
                    .onErrorResumeNext {
                        if (it is UnknownHostException){
                            Single.error(Exception( "Uh oh! Looks like you lost your internet connection. " +
                                    "Please check your network settings and try again later."))
                        }
                        else {
                            Single.error(it)
                        }
                    }
            }
        }
    }

    override fun getTrack(): Single<Track> {
        return Single.defer {
            val track = preferenceHelper.getTrack()
            if (track == null){
                Single.error(Exception("No track cached."))
            }else {
                Single.just(track)
            }
        }
    }

    override fun setTrack(track: Track?): Completable {
        return Completable.fromAction {
            preferenceHelper.setTrack(track)
        }
    }

    override fun setLastLogDate(date: String): Completable {
        return Completable.fromAction {
            preferenceHelper.setLastLogDate(date)
        }
    }

    override fun getLastLogDate(): Single<String> {
        return Single.just(preferenceHelper.getLastLogDate())
    }
}