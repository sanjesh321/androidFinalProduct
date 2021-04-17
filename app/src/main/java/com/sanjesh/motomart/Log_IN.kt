package com.sanjesh.motomart

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.sanjesh.motomart.API.Servicebuilder
import com.sanjesh.motomart.DB.UserDB
import com.sanjesh.motomart.Notification.Sender
import com.sanjesh.motomart.Repository.User_Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class Log_IN : AppCompatActivity() {
    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    private lateinit var etUid: EditText
    private lateinit var etPass: EditText
    private lateinit var cbRemember: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_i_n)
        etUid=findViewById(R.id.etUid)
        etPass=findViewById(R.id.etPass)
        cbRemember=findViewById(R.id.cbRemember)
        checkRunTimePermission()
    }
    private fun saveSharedPref() {
        val sharedPref = getSharedPreferences("credentials", MODE_PRIVATE)
        val editor= sharedPref.edit()
        editor.putString("username",etUid.text.toString())
        editor.putString("password", etPass.text.toString())
        editor.apply()
        Toast.makeText(this@Log_IN, "Username and Password saved", Toast.LENGTH_SHORT).show()

    }
    private fun checkRunTimePermission() {
        if (!hasPermission()) {
            requestPermission()
        }
    }
    private fun requestPermission() {
        ActivityCompat.requestPermissions(this@Log_IN, permissions, 1)
    }
    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
                break
            }
        }
        return hasPermission
    }
    private fun validate() :Boolean{
        var flag = true
        if(TextUtils.isEmpty(etUid.text)){
            etUid.error = "Enter username"
            etUid.requestFocus()
            flag = false
        }else if(TextUtils.isEmpty(etPass.text)){
            etPass.error = "Enter password"
            etPass.requestFocus()
            flag = false
        }
        return flag
    }

    fun login(view: View) {
        if(validate()){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repo = User_Repository()
                    val response = repo.checkUser(etUid.text.toString(),etPass.text.toString())
                    if(response.success == true)
                    {
                        Sender(this@Log_IN,"login successfull!!","${etUid.text.toString()}").createHighPriority()
                        var instance = UserDB.getInstance(this@Log_IN).getUserDAO()
                        instance.registerUser(response.data!!)
                        withContext(Dispatchers.Main)
                        {
                            Servicebuilder.token = "Bearer "+response.token
                            if(cbRemember.isChecked){
                                saveSharedPref()
                            }
                            val intent = Intent(this@Log_IN,Main_Activity::class.java)
                            startActivity(intent)
                            Toast.makeText(this@Log_IN, "Logged in", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else
                    {
                        withContext(Dispatchers.Main)
                        {

                            Toast.makeText(this@Log_IN, "Invalid credentials", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                catch (ex:Exception)
                {
                    println(ex.printStackTrace())
                    withContext(Dispatchers.Main)
                    {
                        Toast.makeText(this@Log_IN, "${ex.toString()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
    fun registerRoute(view: View) {
        val intent = Intent(this,Sign_Up::class.java)
        startActivity(intent)
    }
}