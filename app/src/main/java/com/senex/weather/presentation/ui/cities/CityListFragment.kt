package com.senex.weather.presentation.ui.cities

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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.location.LocationServices
import com.senex.weather.R
import com.senex.weather.databinding.FragmentCityListBinding
import com.senex.weather.presentation.common.toast
import com.senex.weather.presentation.ui.cities.recycler.CityRecyclerAdapter
import dagger.android.support.DaggerFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CityListFragment : DaggerFragment() {
    private var _binding: FragmentCityListBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: CityListViewModel by viewModels { factory }

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
            override fun onQueryTextSubmit(cityName: String): Boolean {
                openWeatherFragment(view, cityName)
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        if (isLocationAccessGranted) {
            getLocation()
        } else {
            requestLocationAccess()
        }

        cityRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        location.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                cityRecyclerView.adapter = CityRecyclerAdapter(
                    viewModel.getCityInfoList(it)
                ) { view, cityName ->
                    openWeatherFragment(view, cityName)
                }
                loadProgressBar.visibility = View.GONE
            }
        }
    }

    // Other than Main thread execution fails
    private fun openWeatherFragment(view: View, cityName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getCityId(cityName)?.let { cityId ->
                navigateToWeatherFragment(
                    cityId,
                    getExtrasForSharedElementTransition(view)
                )
            } ?: requireContext().toast("City not found, try again")
        }
    }

    private fun getExtrasForSharedElementTransition(view: View): FragmentNavigator.Extras {
        val transitionName = resources.getString(R.string.transition_city_name)
        view.transitionName = transitionName
        return FragmentNavigatorExtras(
            view to transitionName
        )
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

    private fun navigateToWeatherFragment(cityId: Int, extras: FragmentNavigator.Extras) =
        findNavController().navigate(
            CityListFragmentDirections.actionCityListFragmentToWeatherFragment(cityId),
            extras,
        )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}