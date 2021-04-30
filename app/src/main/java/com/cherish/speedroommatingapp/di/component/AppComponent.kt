package com.cherish.speedroommatingapp.di.component

import android.app.Application
import com.cherish.speedroommatingapp.SpeedRoomMating
import com.cherish.speedroommatingapp.di.builder.ActivityBuilder
import com.cherish.speedroommatingapp.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder ::class] )
interface AppComponent {
    fun inject(app : SpeedRoomMating)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}