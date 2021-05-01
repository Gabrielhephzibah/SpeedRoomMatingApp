package com.cherish.speedroommatingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cherish.speedroommatingapp.data.AppDataManager
import com.cherish.speedroommatingapp.utils.SchedulerProvider
import com.cherish.speedroommatingapp.viewmodel.UpcomingEventsViewModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ViewModelProviderFactory @Inject constructor(var appDataManger : AppDataManager, var schedulerProvider: SchedulerProvider) :
    ViewModelProvider.NewInstanceFactory() {



    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpcomingEventsViewModel::class.java)){
            return UpcomingEventsViewModel(appDataManger, schedulerProvider) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }

    }



}