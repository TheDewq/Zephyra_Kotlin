package com.zephyra.kotlin_app.singleton_objects

import android.widget.Toast
import com.zephyra.kotlin_app.db.models.productos
import com.zephyra.kotlin_app.db.models.productosItem
import kotlin.properties.Delegates

object carrito_manager {
    var current_list = ArrayList<carrito_cantidades>()
    var total = 0

    fun addItem(item:productosItem):Boolean{
        for (i in current_list){
            if (i.producto == item){
                return false
            }
        }
        current_list.add(carrito_cantidades(item,1, newtotal( item.precio.toInt(), 1)))
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
        }else if(current_cantidad < 0){
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

    }
}
