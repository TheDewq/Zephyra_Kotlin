package com.zephyra.kotlin_app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zephyra.kotlin_app.databinding.CarruselBinding

class itemAdapter(private var itemlista:List<itemsModel>): RecyclerView.Adapter<itemAdapter.itemViewHolder>() {
    class itemViewHolder(val binding: CarruselBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        val binding = CarruselBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return itemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemlista.size
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val item = itemlista[position]
        holder.binding.apply {
            itemFirstPhoto
        }

    }
}