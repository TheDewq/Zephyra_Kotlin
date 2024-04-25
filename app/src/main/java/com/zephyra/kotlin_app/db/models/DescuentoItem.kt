package com.zephyra.kotlin_app.db.models

data class DescuentoItem(
    val fecha_creacion: String,
    val id: String,
    val porcentaje: String,
    var wsp_num: String
)