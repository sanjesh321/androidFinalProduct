package com.sanjesh.motomart.Repository

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class Product_Repository:MyApiRequest() {
    val MyAPI = ServiceBuilder.buildServices(ProductAPI::class.java)

    suspend fun retrieveProducts():ProductResponse{
        return apiRequest {
            MyAPI.retrieveProducts()
        }
    }
    suspend fun insertBulkProduct(context: Context, products: List<Product>){
        //Delete all students
        UserDB.getInstance(context).getProductDAO().deleteAll()
        //Insert all data into DB
        UserDB.getInstance(context).getProductDAO().addProduct(products as MutableList<Product>)
    }
    suspend fun getAllProductFromRoom(context: Context):MutableList<Product>{
        return UserDB.getInstance(context).getProductDAO().getAllProduct()
    }



}