package com.senex.weather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.senex.weather.data.repositories.WeatherRepository
import com.senex.weather.databinding.FragmentWeatherBinding
import kotlinx.coroutines.launch

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

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ): Unit = with(binding) {
        lifecycleScope.launch {
            val weather = repository.getWeather(args.cityId)

            cityName.text = weather.name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}