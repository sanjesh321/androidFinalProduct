package com.sanjesh.motomart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.sanjesh.motomart.Entity.User
import com.sanjesh.motomart.Repository.User_Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Sign_Up : AppCompatActivity() {

    private lateinit var etFn:EditText
    private lateinit var etln:EditText
    private lateinit var etEmail:EditText
    private lateinit var etUsername:EditText
    private lateinit var etPass:EditText
    private lateinit var etConfPass:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        etFn = findViewById(R.id.etFn)
        etln = findViewById(R.id.etln)
        etEmail = findViewById(R.id.etEmail)
        etUsername = findViewById(R.id.etUsername)
        etPass = findViewById(R.id.etPass)
        etConfPass = findViewById(R.id.etConfPass)


    }

    fun registerRoute(view: View) {
        val fn = etFn.text.toString()
        val ln = etln.text.toString()
        val userName = etUsername.text.toString()
        val email = etEmail.text.toString()
        val Password = etPass.text.toString()
        val conPassword = etConfPass.text.toString()
        if(Password != conPassword){
            etPass.error = "Password don't match"
            etPass.requestFocus()
        }
        else{
            val user = User(si_Firstname=fn, si_Lastname=ln, si_Username =userName, si_Email =email, si_password =Password)
            try{


                CoroutineScope(Dispatchers.IO).launch {
                    val userRepo = User_Repository()
                    val response = userRepo.registerUser(user)
                    if(response.message==true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@SignUpActivity, "User Registered", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@SignUpActivity,LoginActivity::class.java)
                            startActivity(intent)
                        }
                    }
                    else
                    {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@SignUpActivity,"Error", Toast.LENGTH_SHORT).show()

                        }
                    }

                }

            }
            catch(ex:Exception)
            {
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@SignUpActivity, "User already exists", Toast.LENGTH_SHORT).show()
                    }
                }


            }
        }


    }
}