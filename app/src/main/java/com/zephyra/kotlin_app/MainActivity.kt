package com.zephyra.kotlin_app

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock.sleep
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.zephyra.kotlin_app.databinding.ActivityMainBinding
import com.zephyra.kotlin_app.db.DbManagerSevice
import com.zephyra.kotlin_app.db.models.productos
import com.zephyra.kotlin_app.singleton_objects.carrito_manager
import com.zephyra.kotlin_app.singleton_objects.data_manager
import com.zephyra.kotlin_app.singleton_objects.productos_manager
import com.zephyra.kotlin_app.ui.activity_order_confirmada.activity_orden_confirmada
import com.zephyra.kotlin_app.ui.activity_start.activity_start
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        init_start()

    }

    private fun init_start() {
        val intent = Intent(this, activity_start::class.java)
        startActivity(intent)
        finish()
    }




    companion object {
        fun startinit() {
            TODO("Not yet implemented")
        }
    }
}


