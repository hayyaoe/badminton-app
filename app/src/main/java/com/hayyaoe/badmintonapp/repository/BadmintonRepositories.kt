package com.hayyaoe.badmintonapp.repository

import android.net.Uri
import android.util.Log
import com.hayyaoe.badmintonapp.model.APIResponse
import com.hayyaoe.badmintonapp.model.CreateGameResponse
import com.hayyaoe.badmintonapp.model.CreateGameResult
import com.hayyaoe.badmintonapp.model.CreateReviewRequest
import com.hayyaoe.badmintonapp.model.CreateSpartnerRequest
import com.hayyaoe.badmintonapp.model.GameData
import com.hayyaoe.badmintonapp.model.Games
import com.hayyaoe.badmintonapp.model.GetGameData
import com.hayyaoe.badmintonapp.model.GetSets
import com.hayyaoe.badmintonapp.model.GetSpartnerRequests
import com.hayyaoe.badmintonapp.model.GetSpartnersResponse
import com.hayyaoe.badmintonapp.model.GetUser
import com.hayyaoe.badmintonapp.model.History
import com.hayyaoe.badmintonapp.model.HistoryResponse
import com.hayyaoe.badmintonapp.model.JoinGameRequest
import com.hayyaoe.badmintonapp.model.Location
import com.hayyaoe.badmintonapp.model.LoginRequest
import com.hayyaoe.badmintonapp.model.OtherUser
import com.hayyaoe.badmintonapp.model.OtherUserData
import com.hayyaoe.badmintonapp.model.Set
import com.hayyaoe.badmintonapp.model.UpdateGameRequest
import com.hayyaoe.badmintonapp.model.UpdateProfilePict
import com.hayyaoe.badmintonapp.model.UpdateSetRequest
import com.hayyaoe.badmintonapp.model.UpdateSpartnerRequest
import com.hayyaoe.badmintonapp.model.UpdateSpartnerResponse
import com.hayyaoe.badmintonapp.model.UpdateUser
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.model.UserGames
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
        Log.d("REGISTER USER DATA", user.toString())
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
        Log.d("test123",  BadmintonContainer.EMAIL)
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

    suspend fun create_game(): CreateGameResponse {
        return badmintonDBServices.create_game(GetUser(BadmintonContainer.EMAIL))
    }

    suspend fun getUserGames(): HistoryResponse {
        return badmintonDBServices.getUserGames(GetUser(email = BadmintonContainer.EMAIL))
    }

    suspend fun create_set(game_id: Int): Set{
        return badmintonDBServices.create_set(GetSets(game_id))
    }

    suspend fun update_set(id: Int, score_1: Int, score_2: Int): Set{
        return badmintonDBServices.update_set(UpdateSetRequest(id= id, player1_score = score_1, player2_score = score_2))
    }

    suspend fun update_game(gamecode: String, gamestatus: Int, information: String, score_1: Int, score_2: Int ): CreateGameResponse {
        Log.d("GAME STATUS REQUEST", "$gamestatus")
        return badmintonDBServices.update_game(UpdateGameRequest(gamecode,gamestatus, information, score_1,score_2))
    }

    suspend fun join_game(gameCode: String): CreateGameResponse{
        return badmintonDBServices.join_game(JoinGameRequest(email = BadmintonContainer.EMAIL, gamecode = gameCode))
    }

    suspend fun get_game_datas(gameCode: String): GetGameData{
        return badmintonDBServices.get_game_datas(JoinGameRequest(email = BadmintonContainer.EMAIL, gamecode = gameCode))
    }

    suspend fun create_review(user_id: Int, game_id: Int, review: String): APIResponse{
        return badmintonDBServices.create_review(CreateReviewRequest(user_id = user_id, game_id = game_id, review = review))
    }

    suspend fun get_spartner_requests(user_id: Int): GetSpartnerRequests{
        return badmintonDBServices.get_spartner_requests(GetUser(user_id = user_id))
    }

    suspend fun create_spartner(user1:Int,user2:Int):APIResponse{
        return badmintonDBServices.create_spartner(CreateSpartnerRequest(user1 = user1, user2 = user2))
    }

    suspend fun update_spartner(user_id: Int): UpdateSpartnerResponse{
        return badmintonDBServices.update_spartner(UpdateSpartnerRequest(game_id = user_id))
    }

    suspend fun get_spartners(user_id: Int): GetSpartnersResponse{
        return badmintonDBServices.get_spartners(GetUser(user_id = user_id))
    }


}