package com.sanjesh.motomart.API

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanjesh.motomart.Response.Cart_Response
import retrofit2.Response
import retrofit2.http.*

interface Cart_API {
    @FormUrlEncoded
    @POST("Cart/{productid}")
    suspend fun addToCart(
        @Header("Authorization") token:String,
        @Path("productid") id:String,
        @Field("quantity") quantity:String
    ): Response<Cart_Response>

    @GET("getCart")
    suspend fun retrieveCart(
        @Header("Authorization") token:String
    ): Response<Cart_Response>
    @FormUrlEncoded
    @PUT("cart/update/{id}")
    suspend fun updateCart(
        @Header("Authorization") token:String,
        @Path("id") id:String,
        @Field("quantity") qty:Int
    ): Response<Cart_Response>

    @DELETE("cart/delete/{id}")
    suspend fun deleteCart(
        @Header("Authorization") token:String,
        @Path("id") id:String
    ): Response<Cart_Response>
}