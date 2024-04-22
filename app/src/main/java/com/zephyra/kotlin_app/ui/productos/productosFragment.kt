package com.zephyra.kotlin_app.ui.productos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.zephyra.kotlin_app.R
import com.zephyra.kotlin_app.databinding.FragmentDashboardBinding
import com.zephyra.kotlin_app.db.models.productos
import com.zephyra.kotlin_app.singleton_objects.carrito_manager
import com.zephyra.kotlin_app.singleton_objects.data_manager
import com.zephyra.kotlin_app.singleton_objects.productos_manager
import com.zephyra.kotlin_app.ui.home.itemsModel

class productosFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(productosViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        create_recycler(productos_manager.current_list, root)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun create_recycler(lista:productos, root:View) {
        var recycler: RecyclerView = root.findViewById(R.id.product_recycler)
        val productLista = ArrayList<productModel>()

        val link = "https://solar-blasts.000webhostapp.com/img/"
        for (rpt in lista) {
            val gson = GsonBuilder().create()
            var thelist = gson.fromJson<ArrayList<String>>(rpt.img, object :
                TypeToken<ArrayList<String>>() {}.type)
            println(rpt.precio)
            productLista.add(productModel(rpt.precio, rpt.nombre, "$link${thelist[0]}", rpt.id))
        }
        recycler.adapter = productAdapter(productLista)
        println(root.context)
        recycler.layoutManager = LinearLayoutManager(root.context)
    }
}