package com.appetiser.testapp.common

import io.reactivex.Scheduler

interface ExecutionThread {

    fun getScheduler(): Scheduler
}