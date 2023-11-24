package com.orion.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.orion.menu.TresEnRaya.TresEnRayaActivity
import com.orion.menu.imccalculator.ImcCalculatorActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSaludar = findViewById<Button>(R.id.btnSaludar)

        val btnIMC = findViewById<Button>(R.id.btnIMC)

        val btnTresEnraya = findViewById<Button>(R.id.btnTresEnraya)

        btnSaludar.setOnClickListener { navigateToSaludar() }

        btnIMC.setOnClickListener { navigateToImc() }

        btnTresEnraya.setOnClickListener { navigateTresEnRaya() }
    }

    private fun navigateToImc() {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSaludar() {
        val  intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }

    private fun navigateTresEnRaya() {
        val intent = Intent(this, TresEnRayaActivity::class.java)
        startActivity(intent)
    }
}