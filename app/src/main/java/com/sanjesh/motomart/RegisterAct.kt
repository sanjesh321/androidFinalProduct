package com.sanjesh.motomart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterAct : AppCompatActivity() {
    private lateinit var etname: EditText
    private lateinit var etphone: EditText
    private lateinit var etusername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConpassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        etname = findViewById(R.id.etname)
        etphone = findViewById(R.id.etphone)
        etusername = findViewById(R.id.etusername)
        etPassword = findViewById(R.id.etpassword)
        etConpassword = findViewById(R.id.etconpassword)
        btnRegister = findViewById(R.id.btnregister)
        btnRegister.setOnClickListener {
            val phone = etphone.text.toString()
            val username = etusername.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConpassword.text.toString()
            if (password != confirmPassword) {
                etPassword.error = "Password does not match"
                etPassword.requestFocus()
                return@setOnClickListener
            }
            else {
                val user = User( username, password, phone)
                CoroutineScope(Dispatchers.IO).launch {
                    MotoMartDB.getInstance(this@RegisterAct).getUserDAO()
                        .registerUser(user)
                    withContext(Main) {
                        startActivity(Intent(this@RegisterAct, LogIN::class.java));
                        Toast.makeText(this@RegisterAct, "User Registered", Toast.LENGTH_SHORT)
                            .show();
                    }
                }
            }
        }
    }
}
    }
}