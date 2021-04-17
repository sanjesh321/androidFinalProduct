package com.sanjesh.motomart.Adapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Cart_Adapter(val context: Context, var lstCart:MutableList<Cart>, var refresh:CartRefresh):
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    class CartViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvProduct: TextView
        val tvPrice: TextView
        val ivMinus: ImageView
        val ivAdd: ImageView
        val tvQuantity: TextView
        val ivProduct: ImageView
        val cbCheck: CheckBox
        val progress: ContentLoadingProgressBar
        init {
            tvProduct = view.findViewById(R.id.tvProduct)
            tvPrice = view.findViewById(R.id.tvPrice)
            ivMinus = view.findViewById(R.id.ivMinus)
            ivAdd = view.findViewById(R.id.ivAdd)
            tvQuantity = view.findViewById(R.id.tvQuantity)
            ivProduct = view.findViewById(R.id.ivProduct)
            cbCheck = view.findViewById(R.id.cbCheck)

            progress = view.findViewById(R.id.progressBar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_view, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        var cart = lstCart[position]

        holder.tvProduct.text = cart.p_id!!.pname
        //  holder.tvBrand.text = cart.p_id!!.pdesc
        holder.tvPrice.text = "Rs " + cart.pprice.toString()
        holder.tvQuantity.text = cart.pquantity.toString()


        var imagePath = ServiceBuilder.loadImagePath() + cart.p_id!!.pimage!!.replace("\\", "/")
        Glide.with(context).load(imagePath).into(holder.ivProduct)

        //comes from db
        var newPrice = cart.pprice / cart.pquantity

        //plus minus listener
        holder.ivAdd.setOnClickListener {


            CoroutineScope(Dispatchers.Main).launch {
                holder.progress.visibility = View.VISIBLE

            }
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repo = CartRepository()
                    var quantity = cart.pquantity + 1
                    val response = repo.updateCart(cart._id, quantity)
                    if (response.success == true) {

                        cart.pquantity += 1
                        cart.pprice = newPrice * cart.pquantity
                        withContext(Dispatchers.Main)
                        {
                            notifyDataSetChanged()
                            refresh.refreshCartActivity()
                            holder.progress.visibility = View.GONE
                        }

                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main)
                    {
                        val snk = Snackbar.make(
                            holder.tvProduct,
                            "${ex.toString()}",
                            Snackbar.LENGTH_LONG
                        )
                        snk.setAction("Ok", View.OnClickListener {
                            snk.dismiss()
                        })
                        snk.show()
                        println(ex.printStackTrace())
                    }
                }
            }

        }
        holder.ivMinus.setOnClickListener {

            if (cart.pquantity > 1) {
                CoroutineScope(Dispatchers.Main).launch {
                    holder.progress.visibility = View.VISIBLE

                }
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val repo = CartRepository()
                        var quantity = cart.pquantity - 1
                        val response = repo.updateCart(cart._id, quantity)
                        if (response.success == true) {

                            cart.pquantity -= 1
                            cart.pprice = newPrice * cart.pquantity
                            withContext(Dispatchers.Main)
                            {
                                notifyDataSetChanged()
                                refresh.refreshCartActivity()
                                holder.progress.visibility = View.GONE
                            }

                        } else {
                            withContext(Dispatchers.Main)
                            {
                                val snk = Snackbar.make(
                                    holder.tvPrice,
                                    "${response.message}",
                                    Snackbar.LENGTH_LONG
                                )
                                snk.setAction("Ok", View.OnClickListener {
                                    snk.dismiss()
                                })
                                snk.show()
                                holder.progress.visibility = View.GONE
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main)
                        {
                            val snk = Snackbar.make(
                                holder.tvQuantity,
                                "${ex.toString()}",
                                Snackbar.LENGTH_LONG
                            )
                            snk.setAction("Ok", View.OnClickListener {
                                snk.dismiss()
                            })
                            snk.show()
                            println(ex.printStackTrace())
                        }
                    }
                }
            }


        }
        holder.cbCheck.setOnClickListener {
            if (holder.cbCheck.isChecked == true) {
                if (!StaticCart.myCart.contains(cart)) {
                    StaticCart.myCart.add(cart)
                    refresh.refreshCartActivity()
                }
            } else {
                StaticCart.myCart.remove(cart)
                refresh.refreshCartActivity()
            }
        }
    }

    override fun getItemCount(): Int {
        return lstCart.size
    }

}








