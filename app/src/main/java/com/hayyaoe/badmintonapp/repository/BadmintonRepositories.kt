package com.hayyaoe.badmintonapp.repository

import android.util.Log
import com.hayyaoe.badmintonapp.model.APIResponse
import com.hayyaoe.badmintonapp.model.EmailCheck
import com.hayyaoe.badmintonapp.model.GetUser
import com.hayyaoe.badmintonapp.model.Location
import com.hayyaoe.badmintonapp.model.LoginRequest
import com.hayyaoe.badmintonapp.model.UpdateUser
import com.hayyaoe.badmintonapp.model.User
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.model.UserRegistrationRequest
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.service.BadmintonDBServices
import java.net.HttpURLConnection

class BadmintonRepositories(private val badmintonDBServices: BadmintonDBServices) {

    suspend fun login(email :String, password : String): APIResponse{
        val user = LoginRequest(email = email, password = password)
        val result = badmintonDBServices.login(user)
        return result
    }

    suspend fun logout(): String{
        val result = badmintonDBServices.logout()
        return result.message
    }

    suspend fun registerUser(user: UserRegistrationRequest): APIResponse {
        return badmintonDBServices.register(user)
    }

    suspend fun update_user(user: UpdateUser): APIResponse{
        val result = badmintonDBServices.update_user(user)
        return result
    }

    suspend fun email_check(email: String): APIResponse{
        val result = badmintonDBServices.email_check(email)
        return result.data as APIResponse
    }

    suspend fun all_locations():List<Location>{
        try {
            val listLocation = badmintonDBServices.all_locatons().data

            val data = mutableListOf<Location>()

            for(location in listLocation){
                val loc = Location(
                    id = location.id,
                    region = location.region
                )

                data.add(loc)
            }

            return data
        }catch (e: Exception){
            Log.d("Error11", e.message.toString())
            return mutableListOf()
        }
    }

    suspend fun get_user(): UserData {
        val response = badmintonDBServices.get_user(GetUser(email = BadmintonContainer.EMAIL))
        return response

    }
}