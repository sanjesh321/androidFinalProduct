package com.sanjesh.motomart.DAO

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sanjesh.motomart.Entity.User

interface User_Dao {
    @Insert
    suspend fun registerUser(user:User)
    @Query("Select * from User")
    suspend fun checkUser():User
    @Query("Delete from User")
    suspend fun delete()
}