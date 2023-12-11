package com.orion.menu.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.orion.menu.R
import java.text.DecimalFormat


class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false

    private var currentPeso: Int = 70
    private var currentAge: Int = 0
    private var currentHeight: Int = 120


    private lateinit var viewMale: CardView
    private lateinit var viewFemale:CardView

    private lateinit var textViewAltura: TextView
    private lateinit var rsAltura: RangeSlider

    private lateinit var btnSubstract: FloatingActionButton
    private lateinit var btnPlus: FloatingActionButton

    private lateinit var ViewPeso: TextView

    private lateinit var btnSumaAge: FloatingActionButton
    private lateinit var btnRestaAge: FloatingActionButton
    private lateinit var textViewAge: TextView

    private lateinit var btnCALCULATE : Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)

        initComponent()
        initListener()
        initUI()
    }

    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        textViewAltura = findViewById(R.id.textViewAltura)
        rsAltura = findViewById(R.id.rsAltura)
        btnSubstract = findViewById(R.id.btnSubstract)
        btnPlus = findViewById(R.id.btnPlus)
        ViewPeso= findViewById(R.id.ViewPeso)
        btnSumaAge = findViewById(R.id.btnSumaAge)
        btnRestaAge = findViewById(R.id.btnRestaAge)
        textViewAge = findViewById(R.id.textViewAge)
        btnCALCULATE = findViewById(R.id.btnCALCULATE)
    }
    private fun initListener() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        rsAltura.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            val currentHeight = df.format(value).toInt()
            textViewAltura.text = "$currentHeight cm"
        }

        btnPlus.setOnClickListener {
            currentPeso += 1
            setPeso()
        }
        btnSubstract.setOnClickListener {
            currentPeso -= 1
            setPeso()
        }

        btnRestaAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        btnSumaAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnCALCULATE.setOnClickListener {
            val result = calculateIMC()
            navegateToResult(result)
        }
    }

    private fun navegateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentPeso/(currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()
       // Log.i("daniel","el imc es $result")
    }

    private fun setAge() {
        textViewAge.text = currentAge.toString()
    }

    private fun  setPeso(){ ViewPeso.text = currentPeso.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {

        viewMale.setCardBackgroundColor(getColorBackGround(isMaleSelected))
        viewFemale.setCardBackgroundColor(getColorBackGround(isFemaleSelected))
    }

    private fun getColorBackGround(isSelectedColor:Boolean):Int {
        val colorReference = if(isSelectedColor){

            R.color.background_component_selected
        }else {
            R.color.background_component
        }
       return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setPeso()
        setAge()
    }
}