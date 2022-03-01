package com.senex.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.senex.weather.common.log
import com.senex.weather.data.repositories.WeatherRepository
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val repository by lazy {
        WeatherRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWeather()
    }

    private fun getWeather() {
        lifecycleScope.launch {
            try {
                val response = repository.getWeather("Paris")
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Temp: ${response.main.temp} C",
                    Snackbar.LENGTH_LONG
                ).show()
            } catch (ex: Exception) {
                ex.message.toString().log()
            }
        }
    }
}