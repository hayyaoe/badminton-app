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
    }

    private val BASE_URL = "https://aff3-2001-448a-5130-fa4f-a0bc-dcb7-12c-f1e.ngrok-free.app/api/"

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
