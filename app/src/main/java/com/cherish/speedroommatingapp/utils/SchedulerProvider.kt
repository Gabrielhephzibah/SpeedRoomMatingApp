package com.cherish.speedroommatingapp.utils

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io() : Scheduler
    fun ui() : Scheduler
    fun computation() : Scheduler

}