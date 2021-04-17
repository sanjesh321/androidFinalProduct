package com.sanjesh.motomart.API

import Login_Response
import com.sanjesh.motomart.Entity.User
import com.sanjesh.motomart.Response.User_Response
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface Myapi {
    @POST("/register/user")
    suspend fun registerUser(@Body user: User): Response<UserResponse>

    @FormUrlEncoded
    @POST("/login/User")
    suspend fun checkUser(
        @Field("si_Email") username: String,
        @Field("si_password") password: String
    ): Response<LoginResponse>

    @POST("regProduct")
    suspend fun registerProduct(@Body product:Product):Response<ProductResponse>
    @Multipart
    @PUT("/update/profPicture")
    suspend fun uploadImage(
        @Header("Authorization") token:String,
        @Part file: MultipartBody.Part
    ):Response<LoginResponse>

    @FormUrlEncoded
    @POST("update/details")
    suspend fun editDetails(
        @Header("Authorization") token:String,
        @Field("si_Firstname") fn:String,
        @Field("si_Lastname") ln:String,
        @Field("si_Username") un:String,
        @Field("si_Email") em:String,
    ):Response<LoginResponse>
}