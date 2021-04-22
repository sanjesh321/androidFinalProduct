package com.sanjesh.motomart.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanjesh.motomart.Adapter.Product_Adapter
import com.sanjesh.motomart.Entity.category
import com.sanjesh.motomart.Entity.product
import com.sanjesh.motomart.R
import com.sanjesh.motomart.Repository.Product_Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Home_fragment : Fragment() {
    private lateinit var homeCategory: RecyclerView
    private lateinit var saleGridView: RecyclerView
    //    private lateinit var productView:ConstraintLayout
    private var lstCategory = ArrayList<category>()
    private var lstProduct : MutableList<product> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title


        val view = inflater.inflate(R.layout.fragment_home, container, false)
        homeCategory = view.findViewById(R.id.homeCategory)
        saleGridView = view.findViewById(R.id.saleGridView)
        loadSaleProduct()
        return view
    }
    fun loadSaleProduct() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repo = Product_Repository()
                val response = repo.retrieveProducts()
                lstProduct = response.data!!
                println(lstProduct)
                withContext(Dispatchers.Main) {
                    saleGridView.layoutManager =
                        LinearLayoutManager(context)
                    saleGridView.adapter = Product_Adapter(context!!, lstProduct)
                }
            } catch (ex: Exception) {
                println(ex.printStackTrace())
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }}
