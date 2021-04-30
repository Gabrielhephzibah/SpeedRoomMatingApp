package com.cherish.speedroommatingapp.data


import com.cherish.speedroommatingapp.model.response.UpcomingResponse
import com.google.gson.Gson
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor( var apiService: ApiService ) : ApiService {


    override fun getUpComing(key: String): Flowable<MutableList<UpcomingResponse>> {
        return apiService.getUpComing(key)
    }


}