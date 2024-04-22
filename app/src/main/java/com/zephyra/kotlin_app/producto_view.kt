package com.zephyra.kotlin_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zephyra.kotlin_app.db.models.productos
import com.zephyra.kotlin_app.singleton_objects.data_manager

class producto_view : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val nombre:TextView = findViewById(R.id.)
        val rpt:productos = data_manager.dbrpt
        val current_item = rpt.find { it.id == intent.getStringExtra("ref") }
        // organizar atributos del item seleccionado
        precio.text = current_item!!.precio
    }
}