package com.appetiser.testapp.presenter

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
import javax.inject.Inject

class TrackDetailViewModel

@Inject
constructor(private val trackGateway: TrackGateway,
            private val executionThread: ExecutionThread,
            private val postExecutionThread: PostExecutionThread
) : ViewModel(){

    private val _track = MutableLiveData<LiveState<Track>>()
    val track: LiveData<LiveState<Track>> get() = _track

    private val _submit = MutableLiveData<Event<LiveState<Unit>>>()
    val submit: LiveData<Event<LiveState<Unit>>> get() = _submit

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    init {
        loadTrack()
    }

    private fun loadTrack(){
        trackGateway.getTrack()
            .observeOn(postExecutionThread.getScheduler())
            .subscribeOn(executionThread.getScheduler())
            .doOnSubscribe { _track.postValue(LiveState.Loading) }
            .subscribe({
                _track.postValue(LiveState.Data(it))
            },{
                _track.postValue(LiveState.Error(it))
            })
            .addTo(disposable)
    }

    fun removeTrack(){
        trackGateway.setTrack(null)
            .observeOn(postExecutionThread.getScheduler())
            .subscribeOn(executionThread.getScheduler())
            .doOnSubscribe { _submit.postValue(Event(LiveState.Loading)) }
            .subscribe({
                _submit.postValue(Event(LiveState.Data(Unit)))
            },{
                _submit.postValue(Event(LiveState.Error(it)))
            })
            .addTo(disposable)
    }

}