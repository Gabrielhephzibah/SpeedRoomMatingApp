package com.cherish.speedroommatingapp.di.builder

import com.cherish.speedroommatingapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity


}