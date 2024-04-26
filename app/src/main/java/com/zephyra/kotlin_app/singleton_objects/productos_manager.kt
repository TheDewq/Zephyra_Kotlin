package com.zephyra.kotlin_app.singleton_objects

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.zephyra.kotlin_app.db.models.productos

@SuppressLint("StaticFieldLeak")
object productos_manager {
    lateinit var current_list:productos
    init {
        println("productos inicializados")
    }
    fun make_toast(mensaje:String, context:Context){
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    }
    fun get_new_category(category: String):productos{
        var new_list = productos()
        for(i in data_manager.dbrpt){
            if (i.tipo == category){
                new_list.add(i)
            }
        }
        return new_list
    }

}