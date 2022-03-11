package com.senex.weather.ui.cities.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.senex.weather.R
import com.senex.weather.common.TemperatureLevel
import com.senex.weather.data.entities.CityInfo
import com.senex.weather.databinding.ListItemCityWeatherBinding
import kotlin.math.roundToInt

class CityRecyclerAdapter(
    private val items: List<CityInfo>,
) : RecyclerView.Adapter<CityRecyclerAdapter.CityViewHolder>() {

    inner class CityViewHolder(
        private val binding: ListItemCityWeatherBinding,
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        @SuppressLint("SetTextI18n")
        fun bind(item: CityInfo) = with(binding) {
            val color = TemperatureLevel.get(item.main.temp.roundToInt()).color
            val context = root.context
            temperature.setTextColor(ResourcesCompat.getColor(context.resources, color, context.theme))
            temperature.text = item.main.temp.roundToInt().toString() + " °C"

            cityName.text = item.name
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