package com.hayyaoe.badmintonapp.repository

import com.google.gson.GsonBuilder
import com.hayyaoe.badmintonapp.service.BadmintonDBServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BadmintonContainer {

    companion object {
        var ACCESS_TOKEN = ""
        var EMAIL = ""
        val API_URL = "https://901b-182-1-91-131.ngrok-free.app"
        var GAMECODE = ""
    }



    private val BASE_URL = "$API_URL/api/"

    // OkHttpClient setup
    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(ACCESS_TOKEN)) // Ensure this interceptor is aware of token changes
        .build()

    // Retrofit setup
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create the service using Retrofit
    private val retrofitService: BadmintonDBServices by lazy {
        retrofit.create(BadmintonDBServices::class.java)
    }

    // Initialize BadmintonRepositories
    val badmintonRepositories: BadmintonRepositories by lazy {
        BadmintonRepositories(retrofitService)
    }
}

// Make sure to define the AuthInterceptor, BadmintonDBServices, and BadmintonRepositories elsewhere in your code.
