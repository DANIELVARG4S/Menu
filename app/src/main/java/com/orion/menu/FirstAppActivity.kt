package com.orion.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)


        val btnInicio = findViewById<AppCompatButton>(R.id.btnInicio)
        val name = findViewById<AppCompatEditText>(R.id.name)

        btnInicio.setOnClickListener {
            val nombre =  name.text.toString()//devuelve el valor ingresado
            if (nombre.isNotEmpty()) {
                val intent = Intent(this,ResultActivity::class.java)
                intent.putExtra("EXTRA_NAME", nombre)
                startActivity(intent)
                //Log.i("Daniel", "Button Pulsado ${nombre}")
            }
        }

    }
}