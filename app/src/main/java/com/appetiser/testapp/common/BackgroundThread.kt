package com.appetiser.testapp.common

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class BackgroundThread : ExecutionThread {

    override fun getScheduler(): Scheduler {
        return Schedulers.io()
    }
}