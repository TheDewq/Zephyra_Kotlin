package com.zephyra.kotlin_app.singleton_objects

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.zephyra.kotlin_app.db.models.productosItem
import com.zephyra.kotlin_app.ui.carrito.carritoAdapter
import com.zephyra.kotlin_app.ui.carrito.carritoModel

object carrito_manager {
    var current_list = ArrayList<carrito_cantidades>()
    var total = 0
    lateinit var totalRef:TextView
    lateinit var recyclerRef:RecyclerView
    lateinit var context:Context

    fun addItem(item:productosItem):Boolean{
        for (i in current_list){
            if (i.producto == item){
                println("producto ya en carrito")
                return false
            }
        }

        current_list.add(carrito_cantidades(item,1, newtotal( item.precio.toInt(), 1)))
        println("producto agregado, ref = ${item.id}")
        newcartTotal()
        return true
    }
    fun newtotal(precio:Int, cantidad:Int): Int {
        return (precio * cantidad)
    }
    fun delete_current(){
        current_list.clear()
    }
    fun change_cantidad(item: productosItem, cantidad: Int){
        var current_cantidad = cantidad
        if(current_cantidad > 6){
            for (i in current_list){
                if(i.producto == item){
                    i.cantidad = 6
                }
            }
        }else if(current_cantidad <= 0 || current_cantidad == null){
            println("la cantidad actual es: $current_cantidad")
            for (i in current_list){
                if(i.producto == item){
                    current_list.remove(i)
                }
            }
        }else{
            for (i in current_list){
                if(i.producto == item){
                    i.cantidad = current_cantidad
                }
            }
        }
            for (i in current_list){
                if(i.producto == item){
                    i.total = newtotal(item.precio.toInt(), i.cantidad)
                }
            }
        newcartTotal()

    }

    fun addItembyRef(ref: String) {
        addItem(data_manager.dbrpt.find { it.id == ref }!!)
    }
    fun newcartTotal(){
        total = 0
        for (i in current_list){
            val value1:Int = i.producto.precio.toInt()
            val value2:Int = i.cantidad
            total += (value1 * value2)
        }
    }

    fun change_cantidadbyRef(ref: String, cantidad: Int) {
        change_cantidad(data_manager.dbrpt.find { it.id == ref }!!, cantidad)
    }
    fun update_recycler(){
        val recycler:RecyclerView = recyclerRef
        var lista = ArrayList<carritoModel>()
        var current_cart = carrito_manager.current_list
        val link = "https://solar-blasts.000webhostapp.com/img/"
        for (item in current_cart){
            val gson = GsonBuilder().create()
            var thelist = gson.fromJson<ArrayList<String>>(item.producto.img, object :
                TypeToken<ArrayList<String>>(){}.type)
            lista.add(carritoModel(item.producto.nombre, item.producto.precio, item.cantidad.toString(), "$link${thelist[0]}", item.producto.id))
        }
        recycler.adapter = carritoAdapter(lista)
        recycler.layoutManager = LinearLayoutManager(context)


    }
    fun is_addedbyref(ref: String):Boolean{
        for (i in current_list){
            if(i.producto.id == ref){
                return true
            }
        }
        return false
    }
    fun is_added(item:productosItem):Boolean{
        for (i in current_list){
            if(i.producto == item){
                return true
            }
        }
        return false
    }
    fun update_total(){
        totalRef.text = total.toString()
    }
    fun make_toast(mensaje:String){
        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show()
    }
}
