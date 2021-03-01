package com.sanjesh.motomart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class LogIN : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var btncustomerRegistration: Button
    private lateinit var linearlayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_i_n)

                username = findViewById(R.id.username)
                password = findViewById(R.id.password)
                login = findViewById(R.id.login)
                linearlayout = findViewById(R.id.linearlayout)
                btncustomerRegistration = findViewById(R.id.btncustomerRegistration)
                btncustomerRegistration.setOnClickListener {​​​​​​​​
                    startActivity(Intent(this@LogIN, RegisterAct::class.java))
                }
                login.setOnClickListener {​​​​​​​​
                    login()
                }​​​​​​​​
            }​​​​​​​​
            private fun login() {​​​​​​​​
                val username = username.text.toString()
                val password = password.text.toString()
                CoroutineScope(Dispatchers.IO).launch {​​​​​​​​
                    try {​​​​​​​​
                        val repository = CustomerRepository()
                        val response = repository.checkCustomer(username, password)
                        if (response.Success == true) {​​​​​​​​
                            ServiceBuilder.token = "Bearer " + response.Token
                            startActivity(
                                Intent(
                                    this@LogIN,
                                    DashboardActivity::class.java
                                )
                            )
                            finish()
                        }​​​​​​​​
                        else {​​​​​​​​
                            withContext(Dispatchers.Main) {​​​​​​​​
                                val snack =
                                    Snackbar.make(
                                        linearlayout,
                                        "Invalid credentials",
                                        Snackbar.LENGTH_LONG
                                    )
                                snack.setAction("OK", View.OnClickListener {​​​​​​​​
                                    snack.dismiss()
                                }​​​​​​​​)
                                snack.show()
                            }​​​​​​​​
                        }​​​​​​​​
                    }​​​​​​​​catch (ex: Exception) {​​​​​​​​
                        withCon text(Dispatchers.Main) {​​​​​​​​
                            Toast.makeText(
                                this@LogIN,
                                ex.toString(), Toast.LENGTH_SHORT
                            ).show()
                        }​​​​​​​​
                    }​​​​​​​​
                }​​​​​​​​
            }​​​​​​​​
        }​​​​​​​​






        //yo login activity ho
    }
}