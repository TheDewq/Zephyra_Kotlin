package com.zephyra.kotlin_app

import android.content.Intent
import android.os.Bundle
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
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbRpt:productos
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        dbInvoke()


    }

    private fun dbInvoke() {

            val db_results = DbManagerSevice.makeDbManagerService()
            lifecycleScope.launch {
                dbRpt = db_results.get_all_products()
                data_manager.dbrpt = dbRpt
                productos_manager.current_list = dbRpt
                interfaceInit()
            }

    }
    private fun interfaceInit(){
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_productos, R.id.navigation_carrito
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
}


