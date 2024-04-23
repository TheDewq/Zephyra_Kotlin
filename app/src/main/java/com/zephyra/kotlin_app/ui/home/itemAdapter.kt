package com.zephyra.kotlin_app.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zephyra.kotlin_app.databinding.CarruselBinding
import com.zephyra.kotlin_app.ui.activity_product.producto_view

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
            Glide.with(itemFirstPhoto).load(itemlista[position].link).into(itemFirstPhoto)
            carouselItemPrice.text = itemlista[position].precio.toString()
            homeBottomCarruselItem.setOnClickListener{
                var intent = Intent(homeBottomCarruselItem.context, producto_view::class.java)
                //intent.putExtra("ref",item.ref)
                startActivity(homeBottomCarruselItem.context, intent, null)
            }
        }

    }
}