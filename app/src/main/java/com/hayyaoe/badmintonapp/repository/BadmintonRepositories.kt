package com.hayyaoe.badmintonapp.repository

import android.net.Uri
import android.util.Log
import com.hayyaoe.badmintonapp.model.APIResponse
import com.hayyaoe.badmintonapp.model.CreateGameResult
import com.hayyaoe.badmintonapp.model.GameData
import com.hayyaoe.badmintonapp.model.GetSets
import com.hayyaoe.badmintonapp.model.GetUser
import com.hayyaoe.badmintonapp.model.Location
import com.hayyaoe.badmintonapp.model.LoginRequest
import com.hayyaoe.badmintonapp.model.OtherUser
import com.hayyaoe.badmintonapp.model.OtherUserData
import com.hayyaoe.badmintonapp.model.UpdateProfilePict
import com.hayyaoe.badmintonapp.model.UpdateUser
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.model.UserRegistrationRequest
import com.hayyaoe.badmintonapp.service.BadmintonDBServices
import okhttp3.MultipartBody

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

    suspend fun update_user(user: UpdateUser): UserData{
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
        return badmintonDBServices.get_user(GetUser(email = BadmintonContainer.EMAIL))

    }

    suspend fun upload_picture(image: MultipartBody.Part): String {
        return badmintonDBServices.upload_picture(image)
    }

    suspend fun update_profile_picture(email: String,image_path:String){
        badmintonDBServices.update_profile_picture(data = UpdateProfilePict(email = email, image_path = image_path))
    }

    suspend fun get_users(): List<OtherUser> {
        val users = badmintonDBServices.get_users(GetUser(email = BadmintonContainer.EMAIL)).data

        val data = mutableListOf<OtherUser>()
        for (user in users){
            val userData = OtherUser(
                user.contacts,
                user.id,
                user.location_id,
                user.phone_number,
                user.profile_path,
                user.rank,
                user.username
            )

            data.add(userData)
        }

        return data
    }

    suspend fun create_game(): GameData {
        val createGameData = badmintonDBServices.create_game(GetUser(BadmintonContainer.EMAIL))
        Log.d("CreateGameData", createGameData.toString())

        val sets = badmintonDBServices.get_sets(GetSets( createGameData.game_id))
        Log.d("GetSets", sets.toString())

        val game = badmintonDBServices.get_game(GetSets(createGameData.game_id))
        Log.d("GetGame", game.toString())

        val userGames = badmintonDBServices.get_user_in_a_game(GetSets(createGameData.game_id))
        Log.d("GetUserGames", userGames.toString())

        val user1 = badmintonDBServices.get_user(GetUser(user_id = userGames.data[0].user_id))
        Log.d("UserData",user1.toString())
        val set1 = sets.data[0]
        val set2 = sets.data[1]

        return GameData(game= game,set1 = set1, set2 = set2, set3 = null, user1 = user1, user2 = null)
    }
}