package com.zephyra.kotlin_app.singleton_objects

import com.zephyra.kotlin_app.db.models.productosItem

object carrito_manager {
    var current_list = ArrayList<carrito_cantidades>()
    var total = 0

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
}
