package com.sanjesh.motomart.Repository

import Login_Response
import com.sanjesh.motomart.API.Myapi
import com.sanjesh.motomart.API.MyapiRequest
import com.sanjesh.motomart.API.Servicebuilder
import com.sanjesh.motomart.Entity.User
import com.sanjesh.motomart.Response.User_Response
import okhttp3.MultipartBody

class User_Repository:MyApiRequest() {
    val MyAPI= ServiceBuilder.buildServices(MyAPI::class.java)

    suspend fun registerUser(user : User): UserResponse {
        return apiRequest {
            MyAPI.registerUser(user)
        }
    }
    suspend fun checkUser(username : String, password : String): LoginResponse {
        return apiRequest {
            MyAPI.checkUser(username, password)
        }
    }
    suspend fun uploadImage( body: MultipartBody.Part)
            : LoginResponse {
        return apiRequest {
            MyAPI.uploadImage(ServiceBuilder.token!!, body)
        }
    }
    suspend fun editUser(fn:String,ln:String,em:String,un:String):LoginResponse{
        return apiRequest {
            MyAPI.editDetails(ServiceBuilder.token!!, fn,ln,em,un)
        }
    }
}