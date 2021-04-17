package com.sanjesh.motomart.Repository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Cart_Repository():MyApiRequest() {
    val MyAPI = ServiceBuilder.buildServices(CartAPI::class.java)
    suspend fun addToCart(id:String, quantity:String):CartResponse{
        return apiRequest {
            MyAPI.addToCart(ServiceBuilder.token!!,id,quantity)
        }
    }
    suspend fun retrieveCart(): CartResponse {
        return apiRequest {
            MyAPI.retrieveCart(ServiceBuilder.token!!)
        }
    }
    suspend fun updateCart(id: String, qty: Int): CartResponse {
        return apiRequest {
            MyAPI.updateCart(ServiceBuilder.token!!, id, qty)
        }
    }
    suspend fun deleteCart(id: String): CartResponse {
        return apiRequest {
            MyAPI.deleteCart(ServiceBuilder.token!!, id)
        }
    }

}