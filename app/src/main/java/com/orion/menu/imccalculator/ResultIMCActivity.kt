package com.orion.menu.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.orion.menu.R
import com.orion.menu.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView
    private lateinit var  textViewIMC: TextView
    private lateinit var txtViewDescription: TextView
    private lateinit var btnReCalculate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)

        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        initComponent()
        initUI(result)
        initListener()
    }

    private fun initListener() {
        btnReCalculate.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUI(result: Double ){

        textViewIMC.text = result.toString()

        when(result){
            in 0.00 .. 18.50 ->{ // BAjo peso

                textViewResult.text = getString(R.string.description_bajo_peso)
                textViewResult.setTextColor(ContextCompat.getColor(this,R.color.peso_bajo))
                txtViewDescription.text = getString(R.string.title_Bajo_Peso)
            }
            in 18.51 .. 24.99->{// peso normal

                textViewResult.text = getString(R.string.description_peso_normal)
                textViewResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                txtViewDescription.text = getString(R.string.title_peso_normal)
            }
            in 25.00 .. 29.99->{// Sobre peso

                textViewResult.text = getString(R.string.description_sobre_peso)
                textViewResult.setTextColor(ContextCompat.getColor(this, R.color.sobrepeso))
                txtViewDescription.text = getString(R.string.title_sobre_peso)
            }
            in 30.00 .. 99.00 ->{ //Obesidad

                textViewResult.text =  getString(R.string.description_obesidad)
                textViewResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                txtViewDescription.text = getString(R.string.description_obesidad)
            }
            else -> { //error
                textViewIMC.text = getString(R.string.error)
                textViewResult.text = getString(R.string.error)
                textViewResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                txtViewDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponent() {
        textViewResult = findViewById(R.id.textViewResult)
        textViewIMC = findViewById(R.id.textViewIMC)
        txtViewDescription = findViewById(R.id.txtViewDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }
}