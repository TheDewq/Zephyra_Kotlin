package com.zephyra.kotlin_app.singleton_objects

import androidx.navigation.NavController
import com.zephyra.kotlin_app.db.models.productos

object data_manager{
    lateinit var dbrpt:productos
    lateinit var nav_control: NavController
    var dbrpt_set = false

    init {
        println("objeto iniciado")

    }



}