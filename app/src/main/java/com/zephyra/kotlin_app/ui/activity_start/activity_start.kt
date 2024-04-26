package com.zephyra.kotlin_app.ui.activity_start

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.zephyra.kotlin_app.MainActivity2
import com.zephyra.kotlin_app.R
import com.zephyra.kotlin_app.db.DbManagerSevice
import com.zephyra.kotlin_app.singleton_objects.data_manager
import com.zephyra.kotlin_app.singleton_objects.productos_manager
import kotlinx.coroutines.launch
import java.lang.Exception

class activity_start : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(R.layout.activity_start)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        invoke_db(1)



    }
    fun set_error(count:Int):Int{
        val txt:TextView = findViewById(R.id.start_aviso)
        txt.text = "¡Espera un momento!"
        return count+1
    }
    fun invoke_db( conteo: Int){
        var count = conteo
        val db_results = DbManagerSevice.makeDbManagerService()
        lifecycleScope.launch {
            try {
                val dbRpt = db_results.get_all_products()
                data_manager.dbrpt = dbRpt
                productos_manager.current_list = dbRpt
                val intent = Intent(this@activity_start, MainActivity2::class.java)
                startActivity(intent)
                finish()
            } catch (nose: Exception) {
                count = set_error(count)
                println(nose)
                invoke_db(count)
            }

        }
    }

}