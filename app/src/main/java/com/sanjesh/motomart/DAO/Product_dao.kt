package com.sanjesh.motomart.DAO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanjesh.motomart.Entity.product

interface Product_dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: MutableList<product>)
    @Query("select * from Product")
    suspend fun viewProduct():List<product>
    @Query("delete from Product")
    suspend fun deleteAll()
    @Query("SELECT * FROM Product")
    suspend fun getAllProduct() : MutableList<product>
}