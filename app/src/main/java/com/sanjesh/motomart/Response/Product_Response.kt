package com.sanjesh.motomart.Response

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanjesh.motomart.Entity.product

data class Product_Response(
    val success:Boolean?=null,

    val data:MutableList<product>?=null
)