package com.cherish.speedroommatingapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cherish.speedroommatingapp.BuildConfig
import com.cherish.speedroommatingapp.data.AppDataManager
import com.cherish.speedroommatingapp.model.response.UpcomingResponse
import com.cherish.speedroommatingapp.utils.Resource
import com.cherish.speedroommatingapp.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class UpcomingViewModel(var appDataManager: AppDataManager, var schedulerProvider: SchedulerProvider) : ViewModel() {
    var disposable = CompositeDisposable()

    val upcoming = MutableLiveData<Resource<MutableList<UpcomingResponse>>>()

    fun setUpcomingData(data: Resource<MutableList<UpcomingResponse>>) {
        upcoming.value = data
    }

    fun getUpcomingData(): MutableLiveData<Resource<MutableList<UpcomingResponse>>> {
        return upcoming
    }

    fun getUpcomingEvent() {
        disposable.add(
            appDataManager
                .getUpComing(BuildConfig.SECRET_KEY)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    if (it.isNullOrEmpty()) {
                        setUpcomingData(Resource.Empty(it))
                    } else {
                        setUpcomingData(Resource.Ideal(it))
                    }

                }, {
                    setUpcomingData(Resource.Error(it.message!!, null))
                    it.printStackTrace()
                })
        )

    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}