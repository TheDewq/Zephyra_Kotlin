package com.zephyra.kotlin_app.ui.home

import android.os.Bundle
import android.provider.DocumentsContract.Root
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.zephyra.kotlin_app.R
import com.zephyra.kotlin_app.databinding.FragmentHomeBinding
import com.zephyra.kotlin_app.db.DbManagerSevice
import com.zephyra.kotlin_app.db.models.productoimg
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import kotlin.concurrent.thread


class HomeFragment : Fragment() {

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
        val btn: Button = root.findViewById(R.id.btn_section)
        val itemlista = ArrayList<itemsModel>()
        // carousel functions //
        val db_results = DbManagerSevice.makeDbManagerService()
        lifecycleScope.launch {
            val rpt = db_results.get_all_products()
            val link = "https://solar-blasts.000webhostapp.com/img/"
            for (i in 0..4){
                val gson = GsonBuilder().create()
                var thelist = gson.fromJson<ArrayList<String>>(rpt[i].img, object :
                    TypeToken<ArrayList<String>>(){}.type)
                itemlista.add(itemsModel("$link${thelist[0]}", rpt[i].precio.toInt()))
            }
            val adapter = itemAdapter(itemlista)

            binding.apply {
                carousel.adapter = adapter
                carousel.setInfinite(true)
                carousel.setFlat(true)
            }
        }

        //itemlista.add(itemsModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_GhG2cRGNg5hvvxKqNYp441_uOQCf5xkZn0FCHvygHw&s", 100000))

        // end carousel function //

        // top carousel //
        var init_carousel = carousel_superior().apply {  };
        init_carousel.init_carousel(root.findViewById(R.id.top_image_caroursel))
        // end top carousel//
        print("funciona")
        btn.setOnClickListener(){
            println("funciona2")
        }
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = "TITULO"
            print("funciona")

        }
        // hasta aqui van las funciones del fragmento home
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

