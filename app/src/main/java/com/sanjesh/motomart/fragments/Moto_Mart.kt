package com.sanjesh.motomart.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sanjesh.motomart.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Moto_MartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_motomart, container, false)
    }
}