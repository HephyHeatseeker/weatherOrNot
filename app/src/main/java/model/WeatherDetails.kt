package model

import com.google.gson.annotations.SerializedName

data class WeatherDetails(

    @SerializedName("temp")
    val temperature: Double,

    @SerializedName("humidity")
    val humidity: Int
)
