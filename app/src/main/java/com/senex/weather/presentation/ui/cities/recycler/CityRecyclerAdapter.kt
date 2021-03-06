package com.senex.weather.presentation.ui.cities.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.senex.weather.databinding.ListItemCityWeatherBinding
import com.senex.weather.domain.model.CityInfo
import com.senex.weather.presentation.common.TemperatureLevel
import kotlin.math.roundToInt

class CityRecyclerAdapter(
    private val items: List<CityInfo>,
    private val onItemClickListener: ((View, String) -> Unit)? = null,
) : RecyclerView.Adapter<CityRecyclerAdapter.CityViewHolder>() {

    inner class CityViewHolder(
        private val binding: ListItemCityWeatherBinding,
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        @SuppressLint("SetTextI18n")
        fun bind(item: CityInfo): Unit = with(binding) {
            val color = TemperatureLevel.get(item.main.temp.roundToInt()).color
            val context = root.context
            temperature.setTextColor(ResourcesCompat.getColor(context.resources,
                color,
                context.theme))
            temperature.text = item.main.temp.roundToInt().toString() + " °C"
            cityName.text = item.name

            root.setOnClickListener {
                onItemClickListener?.invoke(it, item.name)
            }
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