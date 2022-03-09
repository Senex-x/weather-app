package com.senex.weather.ui.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.senex.weather.common.log
import com.senex.weather.data.repositories.WeatherRepository
import com.senex.weather.databinding.FragmentWeatherBinding
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding
        get() = _binding!!

    private val args: WeatherFragmentArgs by navArgs()
    private val repository by lazy { WeatherRepository() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentWeatherBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ): Unit = with(binding) {
        lifecycleScope.launch {
            val weather = repository.getWeather(args.cityId)
            loadProgressBar.visibility = View.GONE

            weather.toString().log()

            cityName.text = weather.name
            temperature.text = "${weather.main.temp.roundToInt()} °"
            temperatureMin.text = "${weather.main.temp_min.roundToInt()} °"
            temperatureMax.text = "${weather.main.temp_max.roundToInt()} °"
            weatherDescription.text = weather.weather[0].description
            windSpeed.text = "${weather.wind.speed} m/s" // Should be extracted
            humidity.text = "${weather.main.humidity} %"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}