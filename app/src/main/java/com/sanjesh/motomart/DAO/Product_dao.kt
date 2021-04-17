package com.sanjesh.motomart.DAO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface Product_dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: MutableList<Product>)
    @Query("select * from Product")
    suspend fun viewProduct():List<Product>
    @Query("delete from Product")
    suspend fun deleteAll()
    @Query("SELECT * FROM Product")
    suspend fun getAllProduct() : MutableList<Product>
}