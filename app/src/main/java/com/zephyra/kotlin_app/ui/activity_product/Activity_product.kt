package com.zephyra.kotlin_app.ui.activity_product

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
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
import com.zephyra.kotlin_app.singleton_objects.data_manager
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class producto_view : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        enableEdgeToEdge()
        setContentView(R.layout.activity_producto_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val intent = getIntent()
        intent.getStringExtra("ref")
        //variables de view
        val precio:TextView = findViewById(R.id.activity_product_price)
        val nombre:TextView = findViewById(R.id.activity_product_name)
        val caracteristicas:RecyclerView = findViewById(R.id.activity_producto_recycler)

        //guardar informacion de producto de manera local
        val rpt:productos = data_manager.dbrpt
        val current_item = rpt.find { it.id == intent.getStringExtra("ref") }

        // organizar atributos del item seleccionado
        precio.text = current_item!!.precio
        nombre.text = current_item!!.nombre
        set_top_carousel(dump_json_img(current_item.img))
        set_bottom_recycler(current_item.tallas, current_item.material,current_item.tipo)


    }
    fun dump_json_img(jsonImg:String):ArrayList<String>{
        val gson = GsonBuilder().create()
        var thelist = gson.fromJson<ArrayList<String>>(jsonImg, object :
            TypeToken<ArrayList<String>>(){}.type)
        return thelist
    }
    fun set_top_carousel(imgArray: ArrayList<String>){
        //declarar lista
        val list = mutableListOf<CarouselItem>()
        val link = "https://solar-blasts.000webhostapp.com/img/"
        for (i in imgArray){
            list.add(CarouselItem("$link$i"))
        }
        val carousel:ImageCarousel = findViewById(R.id.activity_product_topCarousel)
        carousel.setData(list)

    }
    fun set_bottom_recycler(tallas: String, material: String, tipo: String){
        var recycler:RecyclerView = findViewById(R.id.activity_producto_recycler)
        val productLista = ArrayList<Activity_productoModel>()
        //tallas
        val tallas_array = dump_json_img(tallas)
        productLista.add(Activity_productoModel("tallas",tallas_array[0]))
        for (i in tallas_array.drop(1)){
            productLista.add(Activity_productoModel("",i))
        }
        productLista.add(Activity_productoModel("material",material))
        productLista.add(Activity_productoModel("tipo",tipo))
        recycler.adapter = Activity_productAdapter(productLista)
        recycler.layoutManager = LinearLayoutManager(this)

    }


}