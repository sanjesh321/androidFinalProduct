package com.sanjesh.motomart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class Category_View
    : AppCompatActivity() {
    private lateinit var categoryProduct: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_view)
        categoryProduct = findViewById(R.id.categoryProduct)
        loadProduct()
    }
    private fun loadProduct(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository = ProductRepository()
                val response = productRepository.retrieveProducts()
                println(response)
                if (response.success == true){
                    //for room
                    productRepository.insertBulkProduct(this@CategoryView,response.data!!)

                }
                var lstProduct = productRepository.getAllProductFromRoom(this@CategoryView)

                withContext(Dispatchers.Main){
                    categoryProduct.adapter= ProductAdapter(this@CategoryView,lstProduct)
                    categoryProduct.layoutManager = LinearLayoutManager(this@CategoryView)



                }
            }catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(
                            this@CategoryView,
                            "Error : ${ex.toString()}", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}