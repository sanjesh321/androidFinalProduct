package com.sanjesh.motomart.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sanjesh.motomart.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Category_fragment : Fragment(),View.OnClickListener,CartRefresh{
    private lateinit var tvCartDesc : TextView
    private lateinit var btnContinue : Button
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: CartAdapter
    private lateinit var btnDelete: Button
    private lateinit var checkout: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_category_fragment, container, false)
        tvCartDesc = view.findViewById(R.id.tvCartDesc)
        btnContinue = view.findViewById(R.id.btnContinue)
        recycler = view.findViewById(R.id.recycler)
        btnDelete = view.findViewById(R.id.btnDelete)
        checkout = view.findViewById(R.id.checkout)
        initialize()
        btnDelete.setOnClickListener(this)
        btnContinue.setOnClickListener(this)
        return view
    }
    private fun initialize()
    {


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cartRepository = CartRepository()
                val response = cartRepository.retrieveCart()
                if (response.success == true) {
                    StaticCart.lstCart = response.data!!
                    println(response)
                    withContext(Dispatchers.Main)
                    {
                        loadData()
                    }

                }
                else
                {

                    withContext(Dispatchers.Main)
                    {
                        val snk = Snackbar.make(recycler,"Not found", Snackbar.LENGTH_LONG)
                        snk.setAction("Cancel",View.OnClickListener {
                            snk.dismiss()
                        })
                        snk.show()
                    }
                }
            }
            catch (ex:Exception)
            {
                withContext(Dispatchers.Main)
                {
                    val snk = Snackbar.make(recycler,"${ex.toString()}", Snackbar.LENGTH_LONG)
                    snk.setAction("Cancel",View.OnClickListener {
                        snk.dismiss()
                    })
                    snk.show()
                    println(ex.printStackTrace())
                }
            }
        }

    }

    private fun loadData()
    {
        if(StaticCart.lstCart.size > 0)
        {
            tvCartDesc.text = "${StaticCart.lstCart.size} items in cart."
            adapter = CartAdapter(requireContext(),StaticCart.lstCart,this)
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(requireContext())
            btnContinue.visibility = View.GONE
        }
        else
        {
            tvCartDesc.text = "No items in cart."
            btnContinue.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.btnDelete->{

                if(StaticCart.myCart.size>0)
                {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val repo = CartRepository()
                            val response = repo.deleteCart(StaticCart.myCart[0]._id)
                            if(response.success == true)
                            {
                                StaticCart.lstCart.remove(StaticCart.lstCart[0])

                                withContext(Dispatchers.Main)
                                {
                                    loadContent()
                                }
                            }
                            else
                            {
                                withContext(Dispatchers.Main)
                                {
                                    val snk = Snackbar.make(recycler,"${response.message}", Snackbar.LENGTH_LONG)
                                    snk.setAction("Ok",View.OnClickListener {
                                        snk.dismiss()
                                    })
                                    snk.show()
                                }
                            }
                        }
                        catch (ex:Exception)
                        {
                            withContext(Dispatchers.Main)
                            {
                                val snk = Snackbar.make(recycler,"${ex.printStackTrace()}", Snackbar.LENGTH_LONG)
                                snk.setAction("Ok",View.OnClickListener {
                                    snk.dismiss()
                                })
                                println(ex.printStackTrace())
                                snk.show()
                            }
                        }
                    }
                }


            }

            R.id.btnContinue->{
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
        }
    }


    override fun refreshCartActivity() {
        if (StaticCart.myCart.size > 0) {

            var prices = StaticCart.myCart.map {
                it.pprice
            }
            var totalPrice = prices.reduce { acc, i ->
                acc + i
            }

            btnDelete.visibility = View.VISIBLE
            checkout.visibility = View.VISIBLE
            checkout.text = "Total Checkout: Rs ${totalPrice}"
        } else {
            btnDelete.visibility = View.GONE
            checkout.visibility = View.GONE
            checkout.text = "Total Checkout: Rs ${0}"

        }
    }
    private fun loadContent()
    {
        adapter = CartAdapter(requireContext(),StaticCart.lstCart,this)
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()
        StaticCart.myCart = mutableListOf()
        btnDelete.visibility=View.GONE
        checkout.visibility = View.GONE
        if(StaticCart.lstCart.size > 0)
        {
            tvCartDesc.text = "${StaticCart.lstCart.size} items in cart."
            btnContinue.visibility = View.GONE
        }
        else
        {
            tvCartDesc.text = "No Items in cart."
            btnContinue.visibility = View.VISIBLE
        }
    }

}

