package com.appetiser.testapp.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appetiser.testapp.common.ExecutionThread
import com.appetiser.testapp.common.PostExecutionThread
import com.appetiser.testapp.data.TrackGateway
import com.appetiser.testapp.presenter.common.event.Event
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SplashViewModel

@Inject
constructor(private val trackGateway: TrackGateway,
            private val executionThread: ExecutionThread,
            private val postExecutionThread: PostExecutionThread
) : ViewModel(){

    private val _showInitialScreen = MutableLiveData<Event<Boolean>>()
    val showInitialScreen: LiveData<Event<Boolean>> get() = _showInitialScreen

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    init {
        checkLastScreen()
    }

    /**
     * check what screen to show
     */
    private fun checkLastScreen(){
        trackGateway.getTrack()
            .observeOn(postExecutionThread.getScheduler())
            .subscribeOn(executionThread.getScheduler())
            .subscribe({
                _showInitialScreen.postValue(Event(false))
            },{
                _showInitialScreen.postValue(Event(true))
            })
            .addTo(disposable)
    }

}