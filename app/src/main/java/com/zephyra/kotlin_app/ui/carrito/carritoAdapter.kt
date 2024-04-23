package com.zephyra.kotlin_app.ui.carrito

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zephyra.kotlin_app.R
import com.zephyra.kotlin_app.singleton_objects.carrito_manager

class carritoAdapter (private val carritoLista:ArrayList<carritoModel>): RecyclerView.Adapter<carritoAdapter.viewHolder>() {
    class viewHolder(view:View) : RecyclerView.ViewHolder(view){
        val titulo:TextView = view.findViewById(R.id.carrito_item_title)
        val precio:TextView = view.findViewById(R.id.carrito_item_price)
        val img:ImageView = view.findViewById(R.id.carrito_item_img)
        val cantidad:TextView = view.findViewById(R.id.carrito_item_quanty)
        val btn_more:FrameLayout = view.findViewById(R.id.carrito_item_btn_upc)
        val btn_less:FrameLayout = view.findViewById(R.id.carrito_item_btn_downc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.item_carrito, parent, false)
        return carritoAdapter.viewHolder(itemview)
    }

    override fun getItemCount() = carritoLista.size

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = carritoLista[position]
        holder.titulo.text = currentItem.titulo
        holder.precio.text = currentItem.precio
        holder.cantidad.setText(currentItem.cantidad)
        holder.btn_more.setOnClickListener {
            //actualizar valor
            carrito_manager.change_cantidadbyRef(currentItem.ref, currentItem.cantidad.toString().toInt()+1)
            //actualizar vista
            carrito_manager.update_recycler()
            //actualizar el total
            carrito_manager.update_total()
        }
        holder.btn_less.setOnClickListener {
            //actualizar valor
            carrito_manager.change_cantidadbyRef(currentItem.ref, currentItem.cantidad.toString().toInt()-1)
            //actualizar vista
            carrito_manager.update_recycler()
            //actualizar el total
            carrito_manager.update_total()
        }
        Glide.with(holder.img).load(currentItem.img).into(holder.img)
    }

}