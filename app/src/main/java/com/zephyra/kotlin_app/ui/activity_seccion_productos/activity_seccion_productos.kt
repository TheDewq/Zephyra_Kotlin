package com.zephyra.kotlin_app.ui.activity_seccion_productos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.zephyra.kotlin_app.R
import com.zephyra.kotlin_app.db.models.productos
import com.zephyra.kotlin_app.singleton_objects.seccion_manager
import com.zephyra.kotlin_app.ui.productos.productAdapter
import com.zephyra.kotlin_app.ui.productos.productModel

class activity_seccion_productos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        enableEdgeToEdge()
        setContentView(R.layout.activity_seccion_productos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnClose:RelativeLayout = findViewById(R.id.activity_seccion_btn_close)

        btnClose.setOnClickListener{
            finish()
        }
        set_recycler(seccion_manager.current_list)
    }
    fun set_recycler(lista:productos){

        var recycler: RecyclerView = findViewById(R.id.activity_seccion_recycler)
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
        recycler.layoutManager = LinearLayoutManager(this)
    }
}