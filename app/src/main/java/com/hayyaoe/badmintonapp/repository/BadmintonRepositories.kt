package com.hayyaoe.badmintonapp.repository

import com.hayyaoe.badmintonapp.model.User
import com.hayyaoe.badmintonapp.service.BadmintonDBServices
import java.net.HttpURLConnection

class BadmintonRepositories(private val badmintonDBServices: BadmintonDBServices) {

    suspend fun login(email :String, password : String): String{
        val user = User(email = email, password = password)
        val result = badmintonDBServices.login(user)
        if (result.status.toInt() == HttpURLConnection.HTTP_OK){
            return result.data as String
        }
        return result.message
    }

    suspend fun logout(): String{
        val result = badmintonDBServices.logout()
        return result.message
    }

    suspend fun register(user: User): String{
        val result = badmintonDBServices.register(user)
        if (result.status.toInt() == HttpURLConnection.HTTP_OK){
            return result.data as String
        }
        return result.message
    }

    suspend fun update_user(user: User): String{
        val result = badmintonDBServices.update_user(user)
        if (result.status.toInt() == HttpURLConnection.HTTP_OK){
            return result.data as String
        }

        return result.message
    }
}