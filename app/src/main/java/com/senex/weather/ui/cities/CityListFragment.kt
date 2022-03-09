package com.senex.weather.ui.cities

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationServices
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

    private val repository by lazy {
        WeatherRepository()
    }
    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireActivity())
    }
    private val location = MutableLiveData<Location?>()

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

        cityRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        if (isLocationAccessGranted) {
            getLocation()
        } else {
            requestLocationAccess()
        }

        location.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                cityRecyclerView.adapter = CityRecyclerAdapter(
                    repository.getWeather(getMap(
                        20,
                        it?.latitude?.toFloat() ?: 49F,
                        it?.longitude?.toFloat() ?: 49F,
                    ))
                )
                cityRecyclerProgressBar.visibility = View.GONE
            }
        }
    }

    private val isLocationAccessGranted
        get() = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestLocationAccess() =
        locationPermissionRequest.launch(
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { getLocation() }

    private fun getLocation() {
        try {
            fusedLocationClient.lastLocation
                .addOnSuccessListener {
                    location.value = it
                }
        } catch (exception: SecurityException) {
            location.value = null
        }
    }

    private fun getMap(
        count: Int,
        baseLat: Latitude,
        baseLon: Longitude,
    ): Map<Latitude, Longitude> {
        val map = mutableMapOf<Latitude, Longitude>()

        for (i in 1..count) {
            map.put(baseLat + i, baseLon + i)
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