package com.zephyra.kotlin_app.ui.productos

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zephyra.kotlin_app.R
import com.zephyra.kotlin_app.producto_view
import com.zephyra.kotlin_app.ui.productos.productAdapter.*

class productAdapter(var productlista:ArrayList<productModel>): RecyclerView.Adapter<productAdapter.Viewholder>() {

    class Viewholder(view:View):RecyclerView.ViewHolder(view) {

        val product_image: ImageView = view.findViewById(R.id.product_image);
        val name: TextView = view.findViewById(R.id.product_name)
        val price: TextView = view.findViewById(R.id.producto_price)
        val ref: TextView = view.findViewById(R.id.ref)
        val btn_agregar:FrameLayout = view.findViewById(R.id.product_btn_agregar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.producto_item, parent, false)
        return Viewholder(itemview)
    }

    override fun getItemCount(): Int {
        return productlista.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val currentItem = productlista[position]
        Glide.with(holder.product_image.context).load(currentItem.imagen).into(holder.product_image)
        holder.name.text = currentItem.nombre
        holder.price.text = currentItem.precio
        holder.ref.text = currentItem.ref
        holder.btn_agregar.setOnClickListener {
            var intent = Intent(holder.btn_agregar.context, producto_view::class.java)
            intent.putExtra("ref",currentItem.ref)
            startActivity(holder.btn_agregar.context, intent, null)
        }
    }

    }





