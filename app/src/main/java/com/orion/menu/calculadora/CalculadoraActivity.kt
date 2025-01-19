package com.orion.menu.calculadora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.orion.menu.R
import org.w3c.dom.Text

class CalculadoraActivity : AppCompatActivity() {

    //0-> nada, 1->suma, 2 -> resta-> , 3 -> mult, 4->div

    var oper: Int = 0

    var numero1: Double = 0.0

    lateinit var resultado:TextView

    lateinit var  resultado2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        resultado = findViewById(R.id.resultado)
        resultado2 = findViewById(R.id.resulatdo2)

        val btnBorrar : Button = findViewById(R.id.btnReset)

        val btnIgual: Button = findViewById(R.id.btnIgual)
        btnIgual.setOnClickListener{
            var numero2: Double = resultado.text.toString().toDouble()
            var resp: Double = 0.0

            when(oper){
                1-> resp  = numero1 + numero2
                2-> resp  = numero1 - numero2
                3-> resp  = numero1 * numero2
                4-> resp  = numero1 / numero2
            }
            resultado.text = resp.toString()
            resultado2.text = ""
        }

        btnBorrar.setOnClickListener{
            resultado2.text = ""
            resultado.text = ""
            numero1 = 0.0
            oper  = 0
        }
    }

   @SuppressLint("SetTextI18n")
   fun presionarDigito(view: View) {


       val num2: String = resultado.text.toString()

       when(view.id) {
           R.id.btnUno -> resultado.text = num2 + "1"
           R.id.btnDos -> resultado.text = num2 + "2"
           R.id.btnTres -> resultado.text = num2 + "3"
           R.id.btnSeis -> resultado.text = num2 + "4"
           R.id.btnCinco -> resultado.text = num2 + "5"
           R.id.btnCuatro -> resultado.text = num2 + "6"
           R.id.btnSiete -> resultado.text = num2 + "7"
           R.id.btnOcho -> resultado.text = num2 + "8"
           R.id.btnNueve -> resultado.text = num2 + "9"
           R.id.btnCero -> resultado.text = num2 + "0"
           R.id.btnPunto -> resultado.text = num2 + "."
       }
   }

    @SuppressLint("SetTextI18n")
    fun  clickOperation(view: View) {

        numero1 = resultado.text.toString().toDouble()

        val num2_text : String = resultado.text.toString()

        resultado.text = ""


        when(view.id) {
            R.id.btnSumar ->{
                resultado2.text = num2_text + "+"
                oper = 1
            }
            R.id.btnResta ->{
                resultado2.text = num2_text + "-"
                oper = 2
            }
            R.id.btnMultiplicacion ->{
                resultado2.text = num2_text + "x"
                oper = 3
            }
            R.id.btnDivision ->{
                resultado2.text = num2_text + "/"
                oper = 4
            }
        }
    }
}