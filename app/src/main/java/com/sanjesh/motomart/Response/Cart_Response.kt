package com.sanjesh.motomart.Response

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanjesh.motomart.Entity.cart

data class Cart_Response(
    val success:Boolean?=null,
    val message:String?=null,
    val data:MutableList<cart>?=null)