package com.cherish.speedroommatingapp.data

import com.cherish.speedroommatingapp.model.response.UpcomingResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("605479c67ffeba41c07de021")
    fun getUpComing(@Header("secret-key") key:String) : Flowable<MutableList<UpcomingResponse>>
}