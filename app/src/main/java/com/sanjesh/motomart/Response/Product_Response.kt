package com.sanjesh.motomart.Response

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

data class Product_Response(
    val success:Boolean?=null,

    val data:MutableList<Product>?=null
)