package com.sanjesh.motomart.Adapter

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sanjesh.motomart.API.Servicebuilder
import com.sanjesh.motomart.Entity.product
import com.sanjesh.motomart.Product_View
import com.sanjesh.motomart.R

class Product_Adapter(
    val context: Context,
    val lstProduct: MutableList<product>
) : RecyclerView.Adapter<Product_Adapter.ProductViewHolder>() {
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvpName: TextView = view.findViewById(R.id.pName)
        val tvpPrice: TextView = view.findViewById(R.id.pPrice)
        val ivpImage: ImageView = view.findViewById(R.id.pImage)
        val button: Button = view.findViewById(R.id.button)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.products, parent, false)
        return ProductViewHolder(view)
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = lstProduct[position]
        holder.tvpName.text = product.pname
        holder.tvpPrice.text = product.pprice.toString()
        var imagepath = Servicebuilder.loadImagePath() + product.pimage
        imagepath = imagepath.replace("\\", "/")
        Glide.with(context).load(imagepath).into(holder.ivpImage)
        holder.button.setOnClickListener {
            var intent = Intent(context,Product_View::class.java)
            intent.putExtra("product",product)
            context.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
        return lstProduct.size
    }
}