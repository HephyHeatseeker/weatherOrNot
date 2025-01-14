package com.yassine.whetherornot.screens

import ViewModel.WeatherViewModel
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue // don't forget to import this otherwise the collectAsState wont be recognised , sometimes the import doesnt happen automatically
import androidx.compose.runtime.setValue // don't forget to import this too
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yassine.weatherornot.R
import android.os.Bundle
import com.yassine.weatherornot.BuildConfig


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel() ) {

    val weather by viewModel.weather.collectAsState()
    var city by remember { mutableStateOf("") }
    var convertedTemperature by remember { mutableStateOf<Double?>(null) }

    var prevUnit by remember { mutableStateOf("") }
    var selectedUnit by remember { mutableStateOf("Kelvin") }
    val temperatureUnits = listOf("Fahrenheit", "Celsius", "Kelvin")

    var expanded by remember { mutableStateOf(false) }


    val apiKey = BuildConfig.WEATHER_API

    print(apiKey)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(20.dp),

            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.weather_title)
        )

        TextField(
            value = city,
            onValueChange = { city = it },
            label = {
                Text(text = stringResource(id = R.string.TEXT_enter_a_location))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (city.isNotEmpty()) {
                    viewModel.fetchWeather(city, apiKey)
                }
            }

        ) {
            Text(
                stringResource(id = R.string.TEXT_get_weather_btn)
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        weather?.let {

            convertedTemperature = convertTemperature(it.weatherDetails.temperature, "Kelvin", selectedUnit)


            Column( modifier = Modifier
                .padding(16.dp)
                , verticalArrangement = Arrangement.Center
                , horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.weather_city) ,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(0.4f)
                    )
                    Text(
                        text = it.cityName,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(0.6f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.weather_Temp) ,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(0.4f)
                    )
                    Text(
                        text = convertedTemperature?.toString() ?: "N/A",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(0.6f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.weather_humidity) ,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(0.4f)
                    )
                    Text(
                        text = it.weatherDetails.humidity.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(0.6f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.weather_details) ,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(0.4f)
                    )
                    Text(
                        text = it.weather[0].description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(0.6f)
                    )
                }


            }





        }


        Row(
            modifier = Modifier
                .padding(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Unit : $selectedUnit",
            )
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "More options")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                temperatureUnits.forEach { unit ->
                    DropdownMenuItem(
                        text = { Text(text = unit) },
                        onClick = {

                            prevUnit = selectedUnit
                            selectedUnit = unit
                            convertedTemperature = if (weather != null) {

                                convertTemperature(weather!!.weatherDetails.temperature, prevUnit, selectedUnit)

                            } else {
                                null
                            }

                            expanded = false
                        }
                    )

                }
            }
        }
    }

}



fun convertTemperature(temp: Double, fromUnit: String, toUnit: String): Double {
    val result  =  when (fromUnit) {

        "Celsius" -> when (toUnit) {
            "Fahrenheit" -> (temp * 9 / 5) + 32
            "Kelvin" -> temp + 273.15
            else -> temp // Celsius
        }
        "Fahrenheit" -> when (toUnit) {
            "Celsius" -> (temp - 32) * 5 / 9
            "Kelvin" -> (temp - 32) * 5 / 9 + 273.15
            else -> temp // Fahrenheit
        }
        "Kelvin" -> when (toUnit) {
            "Celsius" -> temp - 273.15
            "Fahrenheit" -> (temp - 273.15) * 9 / 5 + 32
            else -> temp // Kelvin
        }
        else -> temp // Default case
    }

    return Math.round(result).toDouble()

}

