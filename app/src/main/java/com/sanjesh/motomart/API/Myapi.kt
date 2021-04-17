package com.sanjesh.motomart.API

import Login_Response
import com.sanjesh.motomart.Entity.User
import com.sanjesh.motomart.Response.User_Response
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Myapi {
    @POST("/register")
    suspend fun registerUser(@Body user: User): Response<User_Response>

    @FormUrlEncoded
    @POST("login")
    suspend fun checkUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<Login_Response>
}