package com.senex.weather.ui.cities.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.senex.weather.common.log
import com.senex.weather.data.entities.CityInfo
import com.senex.weather.databinding.ListItemCityWeatherBinding

class CityRecyclerAdapter(
    private val items: List<CityInfo>,
) : RecyclerView.Adapter<CityRecyclerAdapter.CityViewHolder>() {

    inner class CityViewHolder(
        private val binding: ListItemCityWeatherBinding,
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: CityInfo) = with(binding) {
            cityName.text = item.name
            temperature.text = item.main.temp.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ) = CityViewHolder(
        ListItemCityWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
    )

    override fun onBindViewHolder(
        holder: CityViewHolder,
        position: Int,
    ) = holder.bind(items[position])

    override fun getItemCount() = items.size
}