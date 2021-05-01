package com.cherish.speedroommatingapp.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider(val testScheduler: TestScheduler) : SchedulerProvider {
    override fun io(): Scheduler {
       return  testScheduler
    }

    override fun ui(): Scheduler {
       return  testScheduler
    }

    override fun computation(): Scheduler {
       return testScheduler

    }


}