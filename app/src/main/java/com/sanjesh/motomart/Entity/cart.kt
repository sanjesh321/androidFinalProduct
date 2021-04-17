package com.sanjesh.motomart.Entity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class cart(
    @PrimaryKey
    val _id:String = "",
    val p_id:product?=null,
    val u_id:String?=null,
    var pprice:Int=0,
    var pquantity:Int=0
)