package com.sanjesh.motomart.Adapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Sale_Adapter(private val lstSale: ArrayList<ProductSale>,
                  val context: Context
): RecyclerView.Adapter<Sale_Adapter.SaleViewHolder>() {
    class SaleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivSale: ImageView
        val tvSale: TextView

        init {
            ivSale = view.findViewById(R.id.ivSale)
            tvSale = view.findViewById(R.id.tvSale)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.salecategory, parent, false)
        return SaleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SaleViewHolder, position: Int) {
        val ProductSale = lstSale[position]
        holder.tvSale.text = ProductSale.pSale
        Glide.with(context).load(ProductSale.imgSale).into(holder.ivSale)
    }

    override fun getItemCount(): Int {
        return lstSale.size
    }

}