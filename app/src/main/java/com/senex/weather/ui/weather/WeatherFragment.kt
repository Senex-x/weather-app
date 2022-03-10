package com.senex.weather.ui.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.senex.weather.R
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
        toolbar.navigationIcon = ResourcesCompat.getDrawable(
            resources, R.drawable.ic_arrow_back_24, requireContext().theme
        )
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        lifecycleScope.launch {
            val weather = repository.getWeather(args.cityId)
            loadProgressBar.visibility = View.GONE
            informationCardView.visibility = View.VISIBLE

            weather.toString().log()

            cityName.text = weather.name
            temperature.text = "${weather.main.temp.roundToInt()}°"
            temperatureMin.text = "Min ${weather.main.temp_min.roundToInt()} °"
            temperatureMax.text = "Max ${weather.main.temp_max.roundToInt()} °"
            weatherDescription.text = weather.weather[0].description.replaceFirstChar { it.uppercase() }
            humidity.text = "${weather.main.humidity} %"
            windSpeed.text = "${weather.wind.speed} m/s" // Should be extracted
            pressure.text = "${weather.main.pressure} mmHg"
            windDirection.text = "${weather.wind.deg}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}