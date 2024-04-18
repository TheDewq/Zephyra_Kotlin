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
import androidx.viewbinding.ViewBinding
import com.zephyra.kotlin_app.R
import com.zephyra.kotlin_app.databinding.FragmentHomeBinding

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

        // carousel functions //
        val itemlista = ArrayList<itemsModel>()
        itemlista.add(itemsModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_GhG2cRGNg5hvvxKqNYp441_uOQCf5xkZn0FCHvygHw&s", 100000))
        itemlista.add(itemsModel("https://images.wikidexcdn.net/mwuploads/esssbwiki/thumb/f/f2/latest/20160202180900/Kirby_SSB.png/1200px-Kirby_SSB.png", 999999))
        itemlista.add(itemsModel("https://www.geekmi.news/__export/1616108436990/sites/debate/img/2021/03/18/kirby_1_crop1616108354116.jpg_423682103.jpg", 999999))
        itemlista.add(itemsModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWD1hS9adWy-zSydolX3smyn4BsI0T3VH9itRISE4MLQ&s", 999999))
        itemlista.add(itemsModel("https://www.shutterstock.com/image-vector/cute-2d-game-character-kirby-260nw-2171308275.jpg", 999999))

        val adapter = itemAdapter(itemlista)

        binding.apply {
            carousel.adapter = adapter
            carousel.setInfinite(true)
            carousel.setFlat(true)
        }
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

