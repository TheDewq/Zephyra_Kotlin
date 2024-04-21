package com.zephyra.kotlin_app.db

import com.zephyra.kotlin_app.db.models.productos
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface db_manager{
    @GET("productos.php")
    suspend fun get_all_products(


    ): productos
}

object DbManagerSevice{
    fun makeDbManagerService() : db_manager {
        return Retrofit.Builder()
            .baseUrl("https://solar-blasts.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(db_manager::class.java)
    }
}