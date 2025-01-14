package repository

import controller.WeatherApiService
import model.WeatherResponse
import javax.inject.Inject

class WeatherRepository @Inject constructor(
        private val apiService : WeatherApiService
    ) {

    suspend fun getWeather(city : String, apiKey : String) : WeatherResponse {
        return apiService.getWeather(city, apiKey)
    }
}