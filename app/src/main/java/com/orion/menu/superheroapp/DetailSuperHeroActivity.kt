package com.orion.menu.superheroapp

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.orion.menu.R
import com.orion.menu.databinding.ActivityDeatailSuperHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDeatailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeatailSuperHeroBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id:String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)
    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superheroDetail =  getRetrofit().create(ApiService::class.java).getSuperheroDetail(id)

            if (superheroDetail.body() != null) {
                runOnUiThread { createUI(superheroDetail.body()!!) }
            }
        }
    }

    private fun createUI(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        binding.tvSuperHeroName.text = superhero.name
        prepareStates(superhero.powerstats)
        binding.tvSuperHeroRealName.text = superhero.biography.fullName
        binding.tvPublisher.text = superhero.biography.publisher
    }

    private fun prepareStates(powerstats: PowerStatsResponse) {


        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewStrength,powerstats.strength)
        updateHeight(binding.viewPower,powerstats.power)
        updateHeight(binding.viewDurability,powerstats.durability)
        updateHeight(binding.viewIntelligence,powerstats.intelligence)
        updateHeight(binding.viewSpeed,powerstats.speed)

    }

    private fun updateHeight(view:View, stat:String){
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())

        view.layoutParams = params
    }

    private fun pxToDp(px:Float):Int{
       return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px, resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit():Retrofit{
        return  Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}