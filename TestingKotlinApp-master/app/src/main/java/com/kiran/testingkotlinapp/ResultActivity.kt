package com.kiran.testingkotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvOutput = findViewById<TextView>(R.id.tvOutput)

        val result = intent.getIntExtra("result", 0)

        tvOutput.text = "Sum is " + result.toString()


    }
}