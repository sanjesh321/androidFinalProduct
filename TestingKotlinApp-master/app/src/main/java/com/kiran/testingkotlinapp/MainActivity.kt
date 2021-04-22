package com.kiran.testingkotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var etFirst: EditText
    private lateinit var etSecond: EditText
    private lateinit var btnCalculate: Button
    private lateinit var arithmetic: Arithmetic
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFirst = findViewById(R.id.etFirst)
        etSecond = findViewById(R.id.etSecond)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)
        btnCalculate.setOnClickListener {
            addTwoNos()
        }
    }

    private fun addTwoNos() {
        val first = etFirst.text.toString().toInt()
        val second = etSecond.text.toString().toInt()
        arithmetic = Arithmetic()
        arithmetic.first = first
        arithmetic.second = second

        val result = arithmetic.add()


        tvResult.text = result.toString()


//        val intent = Intent(this, ResultActivity::class.java)
//        intent.putExtra("result", result)
//        startActivity(intent)

    }
}