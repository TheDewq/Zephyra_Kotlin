package com.zephyra.kotlin_app.singleton_objects

import android.content.Context
import android.widget.Toast
import com.zephyra.kotlin_app.db.models.productos

object productos_manager {
    lateinit var categoria:String
    lateinit var current_list:productos
    lateinit var context: Context
    init {
        println("productos inicializados")
    }
    fun make_toast(mensaje:String){
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    }

}