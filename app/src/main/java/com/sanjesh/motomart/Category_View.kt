package com.sanjesh.motomart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanjesh.motomart.Adapter.Product_Adapter
import com.sanjesh.motomart.Repository.Product_Repository
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
        setContentView(R.layout.activity_categoryview)
        categoryProduct = findViewById(R.id.categoryProduct)
        loadProduct()
    }
    private fun loadProduct(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository = Product_Repository()
                val response = productRepository.retrieveProducts()
                println(response)
                if (response.success == true){
                    //for room
                    productRepository.insertBulkProduct(this@Category_View,response.data!!)

                }
                var lstProduct = productRepository.getAllProductFromRoom(this@Category_View)

                withContext(Dispatchers.Main){
                    categoryProduct.adapter= Product_Adapter(this@Category_View,lstProduct)
                    categoryProduct.layoutManager = LinearLayoutManager(this@Category_View)



                }
            }catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(
                            this@Category_View,
                            "Error : ${ex.toString()}", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}