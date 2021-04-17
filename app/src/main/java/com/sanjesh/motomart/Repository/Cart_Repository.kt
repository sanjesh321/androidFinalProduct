package com.sanjesh.motomart.Repository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanjesh.motomart.API.Cart_API
import com.sanjesh.motomart.API.MyapiRequest
import com.sanjesh.motomart.API.Servicebuilder
import com.sanjesh.motomart.Response.Cart_Response

class Cart_Repository():MyapiRequest() {
    val MyAPI = Servicebuilder.buildServices(Cart_API::class.java)
    suspend fun addToCart(id:String, quantity:String): Cart_Response {
        return apiRequest {
            MyAPI.addToCart(Servicebuilder.token!!,id,quantity)
        }
    }
    suspend fun retrieveCart(): Cart_Response {
        return apiRequest {
            MyAPI.retrieveCart(Servicebuilder.token!!)
        }
    }
    suspend fun updateCart(id: String, qty: Int): Cart_Response {
        return apiRequest {
            MyAPI.updateCart(Servicebuilder.token!!, id, qty)
        }
    }
    suspend fun deleteCart(id: String): Cart_Response {
        return apiRequest {
            MyAPI.deleteCart(Servicebuilder.token!!, id)
        }
    }

}