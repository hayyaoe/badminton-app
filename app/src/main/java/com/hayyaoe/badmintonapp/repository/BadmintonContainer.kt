package com.hayyaoe.badmintonapp.repository

import com.hayyaoe.badmintonapp.service.BadmintonDBServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BadmintonContainer {


    companion object{
        var ACCESS_TOKEN = ""
    }

    private val BASE_URL = "http://badmintonapi.test/api/"

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(AuthInterceptor(ACCESS_TOKEN))
        .build()

    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    private val retrofitService: BadmintonDBServices by lazy {
        retrofit.create(BadmintonDBServices::class.java)
    }

    val badmintonRepositories:BadmintonRepositories by lazy {
        BadmintonRepositories(retrofitService)
    }
}