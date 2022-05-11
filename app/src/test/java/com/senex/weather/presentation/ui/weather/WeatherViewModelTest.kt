package com.senex.weather.presentation.ui.weather

import com.senex.weather.domain.model.Weather
import com.senex.weather.domain.usecase.GetWeatherByCityId
import com.senex.weather.util.InstantExecutorExtension
import com.senex.weather.util.MainThreadExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class, MainThreadExtension::class)
internal class WeatherViewModelTest {
    private lateinit var viewModel: WeatherViewModel
    private val weather = mockk<Weather>(relaxed = true)

    @BeforeEach
    fun beforeEach() {
        val getWeatherByCityId = mockk<GetWeatherByCityId> {
            coEvery { this@mockk.invoke(CITY_ID) } returns weather
        }
        viewModel = WeatherViewModel(getWeatherByCityId)
    }

    @Test
    fun `Should return Weather via LiveData`() {
        viewModel.getWeather(CITY_ID).observeForever {
            assertEquals(it, weather)
        }
    }

    companion object {
        private const val CITY_ID = 1
    }
}


