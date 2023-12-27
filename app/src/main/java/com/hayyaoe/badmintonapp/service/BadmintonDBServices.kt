package com.hayyaoe.badmintonapp.service

import com.hayyaoe.badmintonapp.model.APIResponse
import com.hayyaoe.badmintonapp.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST

interface BadmintonDBServices {
    @POST("register")
    suspend fun register(@Body user: User) : APIResponse

    @POST("login")
    suspend fun login(@Body user: User) :APIResponse

    @DELETE("logout")
    suspend fun logout(): APIResponse

    @PATCH("update_user")
    suspend fun update_user(@Body user:User): APIResponse
}