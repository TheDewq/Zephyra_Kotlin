package com.zephyra.kotlin_app.ui.activity_product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zephyra.kotlin_app.R

class Activity_productAdapter(val productLista:ArrayList<Activity_productoModel>): RecyclerView.Adapter<Activity_productAdapter.viewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.activy_product_bottom_recycler, parent, false)
        return Activity_productAdapter.viewHolder(itemview)
    }

    override fun getItemCount() = productLista.size

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentitem = productLista[position]
        holder.llave.text = currentitem.llave
        holder.valor.text = currentitem.valor
    }
    class viewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var valor:TextView = view.findViewById(R.id.activity_product_specs_value)
        var llave:TextView = view.findViewById(R.id.activity_product_specs_key)
    }
}