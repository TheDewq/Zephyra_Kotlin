package com.zephyra.kotlin_app.singleton_objects

import com.zephyra.kotlin_app.db.models.productos
import com.zephyra.kotlin_app.db.models.productosItem

data class carrito_cantidades (
    val producto:productosItem,
    var cantidad:Int,
    var total:Int


    )