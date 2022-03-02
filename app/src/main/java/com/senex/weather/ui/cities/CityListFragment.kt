package com.senex.weather.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.senex.weather.common.log
import com.senex.weather.common.toast
import com.senex.weather.data.repositories.Latitude
import com.senex.weather.data.repositories.Longitude
import com.senex.weather.data.repositories.WeatherRepository
import com.senex.weather.databinding.FragmentCityListBinding
import com.senex.weather.ui.cities.recycler.CityRecyclerAdapter
import kotlinx.coroutines.launch

class CityListFragment : Fragment() {
    private var _binding: FragmentCityListBinding? = null
    private val binding
        get() = _binding!!

    private val repository by lazy { WeatherRepository() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentCityListBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ): Unit = with(binding) {
        cityRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            cityRecyclerView.adapter = CityRecyclerAdapter(
                repository.getWeather(getMap(20))
            )
        }

        citySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                lifecycleScope.launch {
                    getCityId(query)?.let {
                        navigateToWeatherFragment(it)
                    } ?: requireContext().toast("City not found, try again")
                }
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })
    }

    private fun getMap(count: Int): Map<Latitude, Longitude> {
        val map = mutableMapOf<Latitude, Longitude>()

        for (i in 1..count) {
            map.put(49.1221F + i, 55.7877F + i)
        }

        return map
    }

    private suspend fun getCityId(
        cityName: String,
    ) = runCatching {
        repository.getWeather(cityName).id
    }.getOrNull()

    private fun navigateToWeatherFragment(cityId: Int) = findNavController().navigate(
        CityListFragmentDirections.actionCityListFragmentToWeatherFragment(
            cityId
        )
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}