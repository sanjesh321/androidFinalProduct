package com.sanjesh.motomart.DAO

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sanjesh.motomart.Entity.User

interface User_Dao {
    @Insert
    suspend fun registerUser(user: User)
    @Query("Select * from User where si_Email=(:Email) and si_password=(:password)")
    suspend fun checkUser(Email: String, password: String):User
    @Delete
    suspend fun delete(user: User)
}