package ViewModel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import model.WeatherResponse
import repository.WeatherRepository
import javax.inject.Inject

@HiltViewModel   // this will tell dagger that this is a viewModel and that it should automatically provide the repository dependency to it
class WeatherViewModel @Inject constructor(

    private val weatherRepository: WeatherRepository

) : ViewModel() {

    private val _weather  = MutableStateFlow<WeatherResponse?>(null)     // mutableStateFlow : state holder that allows us to store the weather data and update it in a reactive way

    val weather : StateFlow<WeatherResponse?> =  _weather


    fun fetchWeather(city : String , apiKey : String){
        viewModelScope.launch{
            val response  = weatherRepository.getWeather(city,apiKey)
            _weather.value = response
        }
    }

}