package com.zephyra.kotlin_app.singleton_objects

import com.zephyra.kotlin_app.db.models.productos
import kotlin.properties.Delegates

object carrito_manager {
    lateinit var current_list:productos
    var total by Delegates.notNull<Int>()
    fun is_init():Boolean{
        return !::current_list.isInitialized
    }
    fun go_to_product_view(){
    }
}
