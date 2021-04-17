package com.sanjesh.motomart.API

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Response
import retrofit2.http.GET

interface Product_API {
    @GET("product/showall")
    suspend fun retrieveProducts(): Response<ProductResponse>
}