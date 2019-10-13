package com.appetiser.testapp.common

import io.reactivex.Scheduler

interface PostExecutionThread {

    fun getScheduler(): Scheduler
}