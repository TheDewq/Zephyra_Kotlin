package com.zephyra.kotlin_app.ui.carrito

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.zephyra.kotlin_app.R
import com.zephyra.kotlin_app.databinding.FragmentNotificationsBinding
import com.zephyra.kotlin_app.singleton_objects.carrito_manager
import com.zephyra.kotlin_app.singleton_objects.productos_manager
import com.zephyra.kotlin_app.ui.home.itemsModel
import com.zephyra.kotlin_app.ui.productos.productAdapter

class carritoFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(carritoViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        productos_manager.context = root.context
        carrito_manager.context = root.context
        set_recycler(root)
        set_total(root)
        val btn_comprar:Button = root.findViewById(R.id.carrito_btn_comprar)
        btn_comprar.setOnClickListener{

        }
        notificationsViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun set_recycler(root:View){
        val recycler:RecyclerView = root.findViewById(R.id.carrito_recycler)
        var lista = ArrayList<carritoModel>()
        var current_cart = carrito_manager.current_list
        val link = "https://solar-blasts.000webhostapp.com/img/"
        for (item in current_cart){
            val gson = GsonBuilder().create()
            var thelist = gson.fromJson<ArrayList<String>>(item.producto.img, object :
                TypeToken<ArrayList<String>>(){}.type)
            lista.add(carritoModel(item.producto.nombre, item.producto.precio, item.cantidad.toString(), "$link${thelist[0]}", item.producto.id))
        }
        recycler.adapter = carritoAdapter(lista)
        recycler.layoutManager = LinearLayoutManager(root.context)
        //pasar referencias al carrito manager
        carrito_manager.recyclerRef = recycler
    }
    fun set_total(root:View){
        val total:TextView = root.findViewById(R.id.carrito_total)
        total.text = carrito_manager.total.toString()
        carrito_manager.totalRef = total
    }
}