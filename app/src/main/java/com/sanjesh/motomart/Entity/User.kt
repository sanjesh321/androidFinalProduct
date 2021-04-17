package com.sanjesh.motomart.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var si_Firstname: String? = null,
    var si_Lastname: String? = null,
    var si_Username: String? = null,
    var si_Email: String? = null,
    var si_password: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}