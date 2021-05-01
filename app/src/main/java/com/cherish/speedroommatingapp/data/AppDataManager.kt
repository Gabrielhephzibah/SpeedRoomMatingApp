package com.cherish.speedroommatingapp.data


import com.cherish.speedroommatingapp.model.response.UpcomingEventsResponse
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor( var apiService: ApiService ) : ApiService {

    override fun getUpComingEvents(key: String): Flowable<MutableList<UpcomingEventsResponse>> {
        return apiService.getUpComingEvents(key)
    }


}