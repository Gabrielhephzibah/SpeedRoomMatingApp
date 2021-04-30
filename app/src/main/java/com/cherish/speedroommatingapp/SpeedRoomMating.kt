package com.cherish.speedroommatingapp

import android.app.Application
import com.cherish.speedroommatingapp.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SpeedRoomMating : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(application = this )
            .build()
            .inject(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector
}
