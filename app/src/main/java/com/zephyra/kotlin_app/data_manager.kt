package com.zephyra.kotlin_app

import androidx.lifecycle.lifecycleScope
import com.zephyra.kotlin_app.db.DbManagerSevice
import com.zephyra.kotlin_app.db.models.productos
import kotlinx.coroutines.launch

object data_manager{
    lateinit var dbrpt:productos

    init {
        println("objeto iniciado")

    }
    fun printtest(){
        for(i in 1..100){
            println(dbrpt)
            println(i)
        }
    }

}