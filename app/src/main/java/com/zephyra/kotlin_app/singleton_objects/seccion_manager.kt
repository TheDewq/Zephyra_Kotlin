package com.zephyra.kotlin_app.singleton_objects

import com.zephyra.kotlin_app.db.models.productos

object seccion_manager {
    lateinit var current_list:productos
}