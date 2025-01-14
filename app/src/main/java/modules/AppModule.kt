package modules

import controller.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


@Module
@InstallIn(SingletonComponent::class) // makes the module available project-wide
object AppModule {

    @Provides
    fun providesRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun providesWeatherApiService(retrofit : Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }
}