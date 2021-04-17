package com.sanjesh.motomart.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sanjesh.motomart.DAO.User_Dao
import com.sanjesh.motomart.Entity.User

@Database(
    entities = [(User::class)],
    version = 2
)

abstract class UserDB : RoomDatabase() {
    abstract fun getUser_Dao(): User_Dao
    companion object{
        @Volatile
        private var instance: UserDB? = null
        fun getInstance(context: Context): UserDB {
            if (instance == null){
                kotlin.synchronized(UserDB::class){
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }
        private fun buildDatabase(context: Context): UserDB {
            return Room.databaseBuilder(
                context.applicationContext,
                UserDB::class.java,
                "UsersDB"
            ).build()
        }
    }

}
