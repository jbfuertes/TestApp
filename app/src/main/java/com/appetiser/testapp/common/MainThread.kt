package com.appetiser.testapp.common

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class MainThread : PostExecutionThread {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}