package com.sanjesh.motomart.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sanjesh.motomart.R

class Settings_fragments : Fragment() {
    private lateinit var btn: TextView
    private lateinit var Cart: TextView
    private lateinit var Logout: TextView
    private lateinit var aboutUs: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        btn = view.findViewById(R.id.btn)
        Cart = view.findViewById(R.id.Cart)
        Logout = view.findViewById(R.id.Logout)
        aboutUs = view.findViewById(R.id.aboutUs)
        btn.setOnClickListener{
            startActivity(Intent(requireContext(),UpdateActivity::class.java))
        }
//        Cart.setOnClickListener {
//        }
        aboutUs.setOnClickListener {
            startActivity(Intent(requireContext(),SingleProductView::class.java))
        }


        return view
    }
}