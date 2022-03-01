package com.senex.weather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.senex.weather.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding
        get() = _binding!!

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
    ) = with(binding) {

    }
}