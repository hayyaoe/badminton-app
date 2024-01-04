package com.hayyaoe.badmintonapp.service

import com.hayyaoe.badmintonapp.model.APIResponse
import com.hayyaoe.badmintonapp.model.GetUser
import com.hayyaoe.badmintonapp.model.Locations
import com.hayyaoe.badmintonapp.model.LoginRequest
import com.hayyaoe.badmintonapp.model.UpdateUser
import com.hayyaoe.badmintonapp.model.User
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.model.UserRegistrationRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface BadmintonDBServices {
    @POST("register")
    suspend fun register(@Body user: UserRegistrationRequest) : APIResponse

    @POST("login")
    suspend fun login(@Body user: LoginRequest) : APIResponse

    @DELETE("logout")
    suspend fun logout(): APIResponse

    @PATCH("update_user")
    suspend fun update_user(@Body user: UpdateUser): APIResponse

    @POST("email_check")
    suspend fun email_check(@Body email: String) :APIResponse

    @GET("all_location")
    suspend fun all_locatons(): Locations

    @POST("get_user")
    suspend fun get_user(@Body email: GetUser): UserData
}