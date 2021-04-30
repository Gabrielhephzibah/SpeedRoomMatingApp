package com.cherish.speedroommatingapp.di.module


import com.cherish.speedroommatingapp.BuildConfig
import com.cherish.speedroommatingapp.data.ApiService
import com.cherish.speedroommatingapp.utils.AppSchedulerProvider
import com.cherish.speedroommatingapp.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        val requestTimeOut = 15
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(requestTimeOut.toLong(), TimeUnit.SECONDS)
            .readTimeout(requestTimeOut.toLong(), TimeUnit.SECONDS)
            .build()

    }

    @Singleton
    @Provides
    fun provideHttpLogger() : HttpLoggingInterceptor {
        return  HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    }

    @Singleton
    @Provides
    fun  provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    }
    @Provides
    fun provideSchedulerProvider() : SchedulerProvider {
        return AppSchedulerProvider()

    }

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}