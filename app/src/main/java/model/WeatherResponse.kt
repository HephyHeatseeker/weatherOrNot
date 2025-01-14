package model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @SerializedName("name")
    val cityName: String,

    @SerializedName("main")
    val weatherDetails: WeatherDetails,   // mapping "main" to the weatherDetails

    @SerializedName("weather")
    val weather: List<Weather>
)
