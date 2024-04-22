package com.zephyra.kotlin_app.singleton_objects

import com.zephyra.kotlin_app.db.models.productos

object productos_manager {
    lateinit var categoria:String
    lateinit var current_list:productos
    init {
        println("carrito inicializado")
    }

}