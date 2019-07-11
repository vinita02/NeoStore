package com.example.neostore.dagger

import com.example.neostore.api.Api
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit


@Module
class AppModule {

    val BASE_URL = "http://staging.php-dev.in:8844/trainingapp/"
    @Provides
    fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiServices(): Api {
        return provideRetrofit(BASE_URL).create(Api::class.java)
    }



}