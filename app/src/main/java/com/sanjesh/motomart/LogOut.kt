package com.sanjesh.motomart

import android.app.Activity
import android.content.Context
import android.content.Intent

class LogOut(val activity: Activity, val context: Context) {
    fun logout()
    {
        var pref = activity.getSharedPreferences("credentials", Context.MODE_PRIVATE)
        var editor = pref.edit()
        editor.clear()
        editor.apply()
        val intent = Intent(context,Log_IN::class.java)
        context.startActivity(intent)
        activity.finish()
    }
}