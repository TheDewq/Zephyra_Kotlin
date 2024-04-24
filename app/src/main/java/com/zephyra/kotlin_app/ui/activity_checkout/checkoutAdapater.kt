package com.zephyra.kotlin_app.ui.activity_checkout

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zephyra.kotlin_app.R

class checkoutAdapater(private val checkoutLista:ArrayList<checkoutModel>):
    RecyclerView.Adapter<checkoutAdapater.viewHolder>() {
    class viewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titulo:TextView = view.findViewById(R.id.checkout_item_title)
        val precio:TextView = view.findViewById(R.id.checkout_item_price)
        val cantidad:TextView = view.findViewById(R.id.checkout_item_quanty)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}