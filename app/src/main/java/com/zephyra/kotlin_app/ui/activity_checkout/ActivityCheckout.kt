package com.zephyra.kotlin_app.ui.activity_checkout

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.zephyra.kotlin_app.R
import com.zephyra.kotlin_app.db.DbManagerSevice
import com.zephyra.kotlin_app.singleton_objects.carrito_cantidades
import com.zephyra.kotlin_app.singleton_objects.carrito_manager
import com.zephyra.kotlin_app.ui.activity_order_confirmada.activity_orden_confirmada
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class activityCheckout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        //lista de productos
        val current_lista = carrito_manager.current_list
        //textview
        val txt_total:TextView = findViewById(R.id.checkout_total)
        val txt_descuento:TextView = findViewById(R.id.checkout_txt_descuento)
        //Edittext
        val editWsp:EditText = findViewById(R.id.checkout_wsp_num)
        //botones
        val btn_close:RelativeLayout = findViewById(R.id.activity_seccion_btn_close)
        val btn_comprobar_codigo:Button = findViewById(R.id.checkout_btn_comprobar_code)
        val btn_finalizar:RelativeLayout = findViewById(R.id.checkout_btn_finalizar)
        //indicadores
        val indicador_codigo:ImageView = findViewById(R.id.checkout_indicador_codigo)

        // variable de funcionamiento

        var total by Delegates.notNull<Double>()
        var descuento = 0.0 //porcentaje en decimal
        var wsp by Delegates.notNull<Long>()
        var wsp_verification = false
        lateinit var codigo_descuento:String
        var descuento_aplicado = false

        //procesos de inicio
        set_recycler(current_lista)
        //calcular total
        total = calcular_total(current_lista, descuento)
        txt_total.text = total.toString()
        //listeners de botones
        btn_comprobar_codigo.setOnClickListener{
            if(wsp_verification) {
                val db_results = DbManagerSevice.makeDbManagerService()
                lifecycleScope.launch {
                    val rpt = db_results.get_discount_by_wsp(wsp.toString())
                    println(!rpt.isEmpty())
                    if(!rpt.isEmpty()) {
                        if (rpt[0].wsp_num == wsp.toString()) {
                            //respuesta en pantalla
                            txt_descuento.text = "tu descuento fue aplicado con exito!"
                            //procedimientos
                            descuento = rpt[0].porcentaje.toInt() * 0.01
                            total = calcular_total(current_lista, descuento)
                            txt_total.text = total.toString()
                            println(total)
                            println(calcular_total(current_lista, descuento))
                            //cambio de color en el indicador
                            indicador_codigo.setColorFilter(R.color.verde)
                            //verificacion de descuentos
                            descuento_aplicado = true
                        } else {
                            txt_descuento.text =
                                "no posees descuentos en este momento ¡Realiza una compra para obtener 10% de descuento!"
                        }
                    } else {
                        txt_descuento.text =
                            "no posees descuentos en este momento ¡Realiza una compra para obtener 10% de descuento!"
                    }
                }
            } else{
                //respuesta en pantalla
                txt_descuento.text = "el numero de whatsapp que ingresaste no es valido ¡intentalo de nuevo!"
            }
        }
        btn_finalizar.setOnClickListener{
            val gson = Gson()
            println(gson.toJson(current_lista))
            if (wsp_verification){
                val db_results = DbManagerSevice.makeDbManagerService()
                lifecycleScope.launch {

                    val rpt = db_results.post_new_order(wsp,gson.toJson(current_lista),((total-(total*descuento)).toInt()), total.toFloat())
                    val intent = Intent(this@activityCheckout, activity_orden_confirmada::class.java)
                    startActivity(intent)


                }
            }
        }
        btn_close.setOnClickListener{
            finish()
        }
        //listenes de edittext
        editWsp.doAfterTextChanged {
            val temp_wsp = editWsp.text.toString()
            val regex = Regex("""^(3(?:0[0-9]|1[0-9]|2[0-4]))(2\d{6}|[3-9]\d{6}|1\d{6}|[2-9]\d{6})$""")
            if(regex.matches(temp_wsp)){
                wsp = editWsp.text.toString().toLong()
                wsp_verification = true
                btn_finalizar.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.verde))
            }else{
                descuento = 0.0
                wsp_verification = false
                if(descuento_aplicado){
                    txt_descuento.text = "haz cambiado el numero ¡debes volver a verificar tu descuento! "
                    btn_finalizar.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gris_claro))
                    descuento_aplicado = false
                    descuento = 0.0
                    //cambio de color en el indicador
                    indicador_codigo.setColorFilter(R.color.gris_claro)
                    //recalcular el total
                    total = calcular_total(current_lista, descuento)
                    txt_total.text = total.toString()
                }
            }
        }

    }

    private fun calcular_total(items:ArrayList<carrito_cantidades>, descuento:Double): Double {
        var tmptotal = 0.0;

        for (item in items){
            tmptotal+= item.total
        }
        val rpt = tmptotal - (tmptotal * descuento)
        println(rpt)
        return rpt
    }

    private fun set_recycler(items:ArrayList<carrito_cantidades>){
        val recycler:RecyclerView = findViewById(R.id.checkout_recycler)
        val link = "https://solar-blasts.000webhostapp.com/img/"
        var lista = ArrayList<checkoutModel>()
        for (item in items){
            val gson = GsonBuilder().create()
            var thelist = gson.fromJson<ArrayList<String>>(item.producto.img, object :
                TypeToken<ArrayList<String>>(){}.type)
            lista.add(checkoutModel(item.producto.nombre, item.producto.precio, item.cantidad.toString(), "$link${thelist[0]}"))
        }
        recycler.adapter = checkoutAdapater(lista)
        recycler.layoutManager = LinearLayoutManager(this)
    }
}