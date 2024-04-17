package com.zephyra.kotlin_app.ui.home

import android.os.Bundle
import android.provider.DocumentsContract.Root
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zephyra.kotlin_app.R
import com.zephyra.kotlin_app.databinding.FragmentHomeBinding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel

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
        val carousel: ImageCarousel = root.findViewById(R.id.carousel)

        // carousel functions //


        // end carousel function //
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

class carousel_item(){

}