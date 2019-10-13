package com.appetiser.testapp.presenter

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appetiser.testapp.common.ExecutionThread
import com.appetiser.testapp.common.PostExecutionThread
import com.appetiser.testapp.data.TrackGateway
import com.appetiser.testapp.presenter.common.event.Event
import com.appetiser.testapp.presenter.common.state.LiveState
import com.appetiser.testapp.presenter.model.Track
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ListViewModel

@Inject
constructor(private val trackGateway: TrackGateway,
            private val executionThread: ExecutionThread,
            private val postExecutionThread: PostExecutionThread
) : ViewModel(){

    private val _tracks = MutableLiveData<LiveState<List<Track>>>()
    val tracks: LiveData<LiveState<List<Track>>> get() =  _tracks

    private val _submit = MutableLiveData<Event<LiveState<Unit>>>()
    val submit: LiveData<Event<LiveState<Unit>>> get() = _submit

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date

    private val disposable = CompositeDisposable()

    init {
        loadData()
        getCachedDate()
        logCurrentDate()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    /**
     * Load tracks data.
     * @param reload determines whether to invalidate cache
     */
    fun loadData(reload: Boolean = false){
        trackGateway.getTracks(
            term = "star",
            country = "au",
            media = "movie",
            invalidateCache = reload
        )
            .subscribeOn(executionThread.getScheduler())
            .observeOn(postExecutionThread.getScheduler())
            .doOnSubscribe { _tracks.postValue(LiveState.Loading) }
            .subscribe({
                _tracks.postValue(LiveState.Data(it))
            },{
                _tracks.postValue(LiveState.Error(it))
            })
            .addTo(disposable)

    }

    /**
     * Cache track
     * @param track Track to cache
     */
    fun saveTrack(track: Track){
        trackGateway.setTrack(track)
            .subscribeOn(executionThread.getScheduler())
            .observeOn(postExecutionThread.getScheduler())
            .doOnSubscribe { _submit.postValue(Event(LiveState.Loading)) }
            .subscribe({
                _submit.postValue(Event(LiveState.Data(Unit)))
            },{
                _submit.postValue(Event(LiveState.Error(it)))
            })
            .addTo(disposable)
    }

    /**
     * Cache the current Date
     */
    @SuppressLint("SimpleDateFormat")
    private fun logCurrentDate(){
        val date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("MMM/dd/yyyy")
        trackGateway.setLastLogDate(dateFormat.format(date))
            .subscribeOn(executionThread.getScheduler())
            .observeOn(postExecutionThread.getScheduler())
            .subscribe()
            .addTo(disposable)
    }

    /**
     * retrieve last logged date
     */
    private fun getCachedDate(){
        trackGateway.getLastLogDate()
            .subscribeOn(executionThread.getScheduler())
            .observeOn(postExecutionThread.getScheduler())
            .subscribe({
                _date.postValue(it)
            },{
                it.printStackTrace()
            })
            .addTo(disposable)
    }

}