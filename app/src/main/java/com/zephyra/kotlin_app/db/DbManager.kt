package com.zephyra.kotlin_app.db

import com.zephyra.kotlin_app.db.models.Descuento
import com.zephyra.kotlin_app.db.models.productos
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface db_manager{
    @GET("productos.php")
    suspend fun get_all_products(


    ): productos
    @GET("descuentos.php")
    suspend fun get_discount_by_wsp(
        @Query("wsp_num") wsp:String
    ): Descuento
    @POST("ordenes.php")
    suspend fun post_new_order(
        @Query("wsp_num") wsp: Long,
        @Query("productos")productos: String,
        @Query("descuentos")descuento: Int,
        @Query("total")total:Float
    )
}

object DbManagerSevice{
    fun makeDbManagerService() : db_manager {
        return Retrofit.Builder()
            .baseUrl("https://solar-blasts.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(db_manager::class.java)
    }
}