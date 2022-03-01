package com.senex.weather.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.senex.weather.data.entities.CityInfo
import com.senex.weather.databinding.FragmentCityListBinding
import com.senex.weather.ui.cities.recycler.CityRecyclerAdapter

class CityListFragment: Fragment() {
    private var _binding: FragmentCityListBinding? = null
    private val binding
        get() = _binding!!

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
    ) = with(binding) {
        cityRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cityRecyclerView.adapter = CityRecyclerAdapter(listOf(CityInfo("asf", 123),CityInfo("asf", 123),CityInfo("asf", 123),CityInfo("asf", 123),CityInfo("asf", 123),CityInfo("asf", 123),CityInfo("asf", 123),CityInfo("asf", 123),))
    }
}