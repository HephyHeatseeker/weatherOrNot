package controller

import model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {


    @GET("weather")
    suspend fun getWeather(
        @Query("q") city : String,
        @Query("appid") apiKey : String
    ) : WeatherResponse

}