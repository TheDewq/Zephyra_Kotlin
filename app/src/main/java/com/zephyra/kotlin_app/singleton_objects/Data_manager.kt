package com.zephyra.kotlin_app.singleton_objects

import com.zephyra.kotlin_app.db.models.productos

object data_manager{
    lateinit var dbrpt:productos

    init {
        println("objeto iniciado")

    }


}