package com.sanjesh.motomart.API

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Myapi {
    @POST("reg/insert")
    suspend fun registerUser(@Body user: User): Response<UserResponse>

    @FormUrlEncoded
    @POST("login")
    suspend fun checkUser(
        @Field("si_Email") username: String,
        @Field("si_password") password: String
    ): Response<LoginResponse>
}