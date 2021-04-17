package com.sanjesh.motomart.Repository

import Login_Response
import com.sanjesh.motomart.API.Myapi
import com.sanjesh.motomart.API.MyapiRequest
import com.sanjesh.motomart.API.Servicebuilder
import com.sanjesh.motomart.Entity.User
import com.sanjesh.motomart.Response.User_Response
import okhttp3.MultipartBody

class User_Repository:MyapiRequest() {
    val Myapi= Servicebuilder.buildServices(Myapi::class.java)

    suspend fun registerUser(user : User): User_Response {
        return apiRequest {
            Myapi.registerUser(user)
        }
    }
    suspend fun checkUser(username : String, password : String): Login_Response {
        return apiRequest {
            Myapi.checkUser(username, password)
        }
    }
    suspend fun uploadImage( body: MultipartBody.Part)
            : Login_Response {
        return apiRequest {
            Myapi.uploadImage(Servicebuilder.token!!, body)
        }
    }
    suspend fun editUser(fn:String,ln:String,em:String,un:String):Login_Response{
        return apiRequest {
            Myapi.editDetails(Servicebuilder.token!!, fn,ln,em,un)
        }
    }
}