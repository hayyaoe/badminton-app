package com.hayyaoe.badmintonapp.service

import com.hayyaoe.badmintonapp.model.APIResponse
import com.hayyaoe.badmintonapp.model.CreateGameResponse
import com.hayyaoe.badmintonapp.model.CreateGameResult
import com.hayyaoe.badmintonapp.model.Game
import com.hayyaoe.badmintonapp.model.Games
import com.hayyaoe.badmintonapp.model.GetGameData
import com.hayyaoe.badmintonapp.model.GetSets
import com.hayyaoe.badmintonapp.model.GetUser
import com.hayyaoe.badmintonapp.model.History
import com.hayyaoe.badmintonapp.model.HistoryResponse
import com.hayyaoe.badmintonapp.model.JoinGameRequest
import com.hayyaoe.badmintonapp.model.Locations
import com.hayyaoe.badmintonapp.model.LoginRequest
import com.hayyaoe.badmintonapp.model.OtherUser
import com.hayyaoe.badmintonapp.model.OtherUserData
import com.hayyaoe.badmintonapp.model.Set
import com.hayyaoe.badmintonapp.model.Sets
import com.hayyaoe.badmintonapp.model.UpdateGameRequest
import com.hayyaoe.badmintonapp.model.UpdateProfilePict
import com.hayyaoe.badmintonapp.model.UpdateSetRequest
import com.hayyaoe.badmintonapp.model.UpdateUser
import com.hayyaoe.badmintonapp.model.User
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.model.UserGames
import com.hayyaoe.badmintonapp.model.UserRegistrationRequest
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part

interface BadmintonDBServices {
    @POST("register")
    suspend fun register(@Body user: UserRegistrationRequest) : APIResponse

    @POST("login")
    suspend fun login(@Body user: LoginRequest) : APIResponse

    @DELETE("logout")
    suspend fun logout(): APIResponse

    @Multipart
    @POST("upload_picture")
    suspend fun upload_picture(@Part image: MultipartBody.Part) : String
    @PATCH("update_user")
    suspend fun update_user(@Body user: UpdateUser): UserData

    @POST("email_check")
    suspend fun email_check(@Body email: String) :APIResponse

    @GET("all_location")
    suspend fun all_locatons(): Locations

    @POST("get_user")
    suspend fun get_user(@Body email: GetUser): UserData

    @POST("get_users")
    suspend fun get_users(@Body email: GetUser): OtherUserData

    @POST("update_profile_picture")
    suspend fun update_profile_picture(@Body data: UpdateProfilePict)

    @POST("create_game")
    suspend fun create_game(@Body email: GetUser): CreateGameResponse

    @POST("get_sets")
    suspend fun get_sets(@Body set: GetSets): Sets

    @POST("get_game")
    suspend fun get_game(@Body game: GetSets) : Game

    @POST("get_user_in_a_game")
    suspend fun get_user_in_a_game(@Body game: GetSets): UserGames

    @POST("get_user_games")
    suspend fun getUserGames(@Body email: GetUser): HistoryResponse

    @POST("create_set")
    suspend fun create_set(@Body game_id : GetSets) : Set

    @PATCH("update_set")
    suspend fun update_set(@Body newData: UpdateSetRequest): Set

    @PATCH("update_game")
    suspend fun update_game(@Body newData: UpdateGameRequest): CreateGameResponse

    @POST("join_game")
    suspend fun join_game(@Body gameData: JoinGameRequest) : CreateGameResponse

    @POST("get_game_datas")
    suspend fun get_game_datas(@Body gameData: JoinGameRequest): GetGameData
}