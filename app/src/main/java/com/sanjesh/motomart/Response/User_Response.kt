package com.sanjesh.motomart.Response

import com.sanjesh.motomart.Entity.User

data class User_Response(
    val message:Boolean?=null,
    val data: User?=null
)