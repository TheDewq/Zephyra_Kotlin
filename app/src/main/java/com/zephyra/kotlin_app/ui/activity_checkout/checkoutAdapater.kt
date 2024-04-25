package com.zephyra.kotlin_app.ui.activity_checkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zephyra.kotlin_app.R
import com.zephyra.kotlin_app.ui.carrito.carritoAdapter

class checkoutAdapater(private val checkoutLista:ArrayList<checkoutModel>):
    RecyclerView.Adapter<checkoutAdapater.viewHolder>() {
    class viewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titulo:TextView = view.findViewById(R.id.checkout_item_title)
        val precio:TextView = view.findViewById(R.id.checkout_item_price)
        val cantidad:TextView = view.findViewById(R.id.checkout_item_quanty)
        val img:ImageView = view.findViewById(R.id.checkout_item_img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.checkout_item, parent, false)
        return checkoutAdapater.viewHolder(itemview)
    }

    override fun getItemCount() = checkoutLista.size

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = checkoutLista[position]
        holder.titulo.text = currentItem.nombre
        holder.precio.text = currentItem.precio
        holder.cantidad.text = currentItem.cantidad
        Glide.with(holder.img).load(currentItem.imagen).into(holder.img)
    }
}