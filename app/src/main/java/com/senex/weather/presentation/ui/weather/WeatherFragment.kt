package com.senex.weather.presentation.ui.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.senex.weather.R
import com.senex.weather.databinding.FragmentWeatherBinding
import com.senex.weather.domain.model.Weather
import com.senex.weather.presentation.common.WeatherState
import com.senex.weather.presentation.common.WindDirection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class WeatherFragment : DaggerFragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: WeatherViewModel by viewModels { factory }

    private val args: WeatherFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
        super.onCreate(savedInstanceState)
    }

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

        viewModel.getWeather(args.cityId).observe(viewLifecycleOwner) {
            loadProgressBar.visibility = View.GONE
            informationCardView.visibility = View.VISIBLE

            setBackgroundImage(it)

            weather = it

            //temperature.text = "${it.main.temp.roundToInt()}°"
            //temperatureMin.text = "Min ${it.main.temp_min.roundToInt()} °"
            //temperatureMax.text = "Max ${it.main.temp_max.roundToInt()} °"
            weatherDescription.text = it.weather[0].description
                .replaceFirstChar { c -> c.uppercase() }
            //humidity.text = "${it.main.humidity} %"
            //windSpeed.text = "${it.wind.speed} m/s" // Should be extracted
            //pressure.text = "${it.main.pressure} mmHg" // Should be extracted
            windDirection.text = WindDirection.get(it.wind.deg).toString().lowercase()
                .replaceFirstChar { c -> c.uppercase() }
        }
    }

    private fun FragmentWeatherBinding.setBackgroundImage(weather: Weather) {
        root.background = ResourcesCompat.getDrawable(
            resources,
            WeatherState.get(weather.weather[0].main).background,
            requireContext().theme
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}