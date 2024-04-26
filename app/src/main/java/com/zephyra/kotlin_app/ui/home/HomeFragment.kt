package com.zephyra.kotlin_app.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.zephyra.kotlin_app.R
import com.zephyra.kotlin_app.singleton_objects.data_manager
import com.zephyra.kotlin_app.databinding.FragmentHomeBinding
import com.zephyra.kotlin_app.db.models.productos
import com.zephyra.kotlin_app.singleton_objects.productos_manager
import com.zephyra.kotlin_app.singleton_objects.seccion_manager
import com.zephyra.kotlin_app.ui.activity_seccion_productos.activity_seccion_productos


class
HomeFragment() : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        // desde aqui se hacen la funciones del fragmento home
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        //#######   DECLARACIONES  #####################################################
        val root: View = binding.root
        val textView: TextView = binding.textView2
        val btnCamiseta:RelativeLayout = root.findViewById(R.id.home_btn_1)
        val btnPantalones:RelativeLayout = root.findViewById(R.id.home_btn_2)
        val btnHoddies:RelativeLayout = root.findViewById(R.id.home_btn_3)
        val btnZapatos:RelativeLayout = root.findViewById(R.id.home_btn_4)
        val btnAccesorios:RelativeLayout = root.findViewById(R.id.home_btn_5)
        val nav = findNavController()
        //controles de secciones
        btnCamiseta.setOnClickListener{
            seccion_manager.current_list = productos_manager.get_new_category("camisetas")
            val intent = Intent(btnCamiseta.context, activity_seccion_productos::class.java)
            startActivity(intent)

        }
        btnPantalones.setOnClickListener{
            seccion_manager.current_list = productos_manager.get_new_category("pantalones")
            val intent = Intent(btnCamiseta.context, activity_seccion_productos::class.java)
            startActivity(intent)
        }
        btnHoddies.setOnClickListener{
            seccion_manager.current_list = productos_manager.get_new_category("hoddies")
            val intent = Intent(btnCamiseta.context, activity_seccion_productos::class.java)
            startActivity(intent)
        }
        btnZapatos.setOnClickListener{
            seccion_manager.current_list = productos_manager.get_new_category("zapatos")
            val intent = Intent(btnCamiseta.context, activity_seccion_productos::class.java)
            startActivity(intent)
        }
        btnAccesorios.setOnClickListener{
            seccion_manager.current_list = productos_manager.get_new_category("accesorios")
            val intent = Intent(btnCamiseta.context, activity_seccion_productos::class.java)
            startActivity(intent)
        }

        // botton view carousel functions //

        bottom_carousel(data_manager.dbrpt)


        // top carousel //
        var init_carousel = carousel_superior().apply {  };
        init_carousel.init_carousel(root.findViewById(R.id.top_image_caroursel))
        // end top carousel//

        homeViewModel.text.observe(viewLifecycleOwner) {

        }
        // hasta aqui van las funciones del fragmento home
        return root
    }

    fun bottom_carousel(rpt:productos){
        val itemlista = ArrayList<itemsModel>()
        val link = "https://solar-blasts.000webhostapp.com/img/"
        for (i in 0..4){
            val gson = GsonBuilder().create()
            var thelist = gson.fromJson<ArrayList<String>>(rpt[i].img, object :
                TypeToken<ArrayList<String>>(){}.type)
            itemlista.add(itemsModel(rpt[i].id,"$link${thelist[0]}", rpt[i].precio.toInt()))
        }
        val adapter = itemAdapter(itemlista)

        binding.apply {
            carousel.adapter = adapter
            carousel.setInfinite(true)
            carousel.setFlat(true)
        }
    }


}




