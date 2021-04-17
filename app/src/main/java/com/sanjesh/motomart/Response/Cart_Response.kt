package com.sanjesh.motomart.Response

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

data class Cart_Response(
    val success:Boolean?=null,
    val message:String?=null,
    val data:MutableList<Cart>?=null)