package com.orion.menu.settings

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.orion.menu.R
import com.orion.menu.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

//funcion de extension

val  Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

    companion object{
        const val VOLUME_LVL ="volume_lvl"

        const val KEY_BLUETOOTH ="key_bluetooth"
        const val KEY_VIBRATION ="key_vibration"
        const val KEY_DARK_MODE ="key_dark_mode"
    }

    private lateinit var binding: ActivitySettingsBinding

    private  var firsTime: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firsTime }.collect{ settingsModel ->
                //datos SettingsModel()
                if (settingsModel != null){
                    runOnUiThread {
                        binding.switchVibration.isChecked = settingsModel.vibration
                        binding.switchBluetooth.isChecked = settingsModel.bluetooth
                        binding.switchDarkMode.isChecked = settingsModel.darMode
                        binding.rsVolumen.setValues(settingsModel.volume.toFloat())
                        firsTime = !firsTime
                    }

                }
            }
        }

        iniUI()
    }

    private fun iniUI() {
        binding.rsVolumen.addOnChangeListener { _, value, _ ->
            Log.i("dani", "El valor es  $value")
            CoroutineScope(Dispatchers.IO).launch {
                saveVolumen(value.toInt())
            }
        }

        binding.switchBluetooth.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_BLUETOOTH,value)
            }

        }

        binding.switchVibration.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_VIBRATION,value)
            }
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _, value ->
            if (value){
                enableDarkMode()
            }else{
                disableDarkMode()
            }
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARK_MODE,value)
            }
        }
    }

    private suspend fun saveVolumen(value: Int){
        dataStore.edit { preferences->
            preferences[intPreferencesKey(VOLUME_LVL) ]= value
        }
    }

    private suspend fun saveOptions(key:String,value:Boolean){
        dataStore.edit { preferences->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    private fun getSettings():Flow<SettingsMode?>{
        return dataStore.data.map { preferences ->
            SettingsMode(
                volume = preferences[intPreferencesKey(VOLUME_LVL)]?: 50,
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)]?: true,
                darMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)]?: false,
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)]?: true
            )
        }
    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}







