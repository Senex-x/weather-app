package com.senex.weather.presentation.cities

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
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.location.LocationServices
import com.senex.weather.presentation.common.toast
import com.senex.weather.databinding.FragmentCityListBinding
import com.senex.weather.presentation.cities.recycler.CityRecyclerAdapter
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CityListFragment : Fragment() {
    private var _binding: FragmentCityListBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: CityListViewModel by viewModels()

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

        //navigateToWeatherFragment(524901)

        citySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                runBlocking { // If I do this asynchronous it fails
                    viewModel.getCityId(query)?.let {
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
                    viewModel.getCityInfoList(it)
                )
                loadProgressBar.visibility = View.GONE
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