package com.orion.menu.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.orion.menu.R

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var btnUno : Button
    private lateinit var btnDos : Button
    private lateinit var btnTres: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        initComponent()
    }

    private  fun initComponent() {
        btnUno = findViewById(R.id.btnUno)
        btnDos = findViewById(R.id.btnDos)
        btnTres = findViewById(R.id.btnTres)
    }
}