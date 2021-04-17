package com.sanjesh.motomart.Repository

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanjesh.motomart.API.Myapi
import com.sanjesh.motomart.API.MyapiRequest
import com.sanjesh.motomart.API.Product_API
import com.sanjesh.motomart.API.Servicebuilder
import com.sanjesh.motomart.DB.UserDB
import com.sanjesh.motomart.Entity.product
import com.sanjesh.motomart.Response.Product_Response


class Product_Repository:MyapiRequest() {
    val Myapi = Servicebuilder.buildServices(Product_API::class.java)

    suspend fun retrieveProducts(): Product_Response {
        return apiRequest {
            Myapi.retrieveProducts()
        }
    }
    suspend fun insertBulkProduct(context: Context, products: List<product>){
        //Delete all students
        UserDB.getInstance(context).getProductDAO().deleteAll()
        //Insert all data into DB
        UserDB.getInstance(context).getProductDAO().addProduct(products as MutableList<product>)
    }
    suspend fun getAllProductFromRoom(context: Context):MutableList<product>{
        return UserDB.getInstance(context).getProductDAO().getAllProduct()
    }



}