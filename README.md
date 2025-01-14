
# Weather App

A simple Android weather app that fetches weather data for a city or country using the OpenWeatherMap API. Built with **Jetpack Compose**, **Dagger & Hilt** for dependency injection, and **Retrofit** for networking.

## Features

- **Weather Search:** Input a city or country and fetch current weather data.
- **Weather Details:** Displays temperature, humidity, and weather description.
- **Unit Conversion:** Supports temperature unit conversion (Celsius, Fahrenheit, Kelvin).
- **Clean Architecture:** Uses ViewModel, Repository pattern, and Dependency Injection (Dagger & Hilt).

## Requirements

- Android Studio
- JDK 11 or higher
- OpenWeatherMap API Key (required for fetching weather data)

## Setup Instructions

Follow these steps to clone and set up the project:

### 1. Clone the Repository

Clone the repository to your local machine:

```bash
git clone https://github.com/your-username/weather-app.git
```

### 2. Create `secret.properties` File

Inside the `app` folder, create a file named `secret.properties` to securely store your API key.

```bash
touch app/secret.properties
```

### 3. Add Your OpenWeatherMap API Key

Inside `secret.properties`, add your OpenWeatherMap API key:

*You can get the API Key by signing in to : https://openweathermap.org/*

```properties
WEATHER_API="YOUR_API_KEY"
```

**Important:** Ensure the API key is enclosed in double quotes (`"`) as shown above. Example:

```properties
WEATHER_API="abcd1234efgh5678"
```

### 4. Sync the Gradle Files

After adding your API key to `secret.properties`, sync the project in Android Studio by clicking **Sync Now** when prompted, or run the following command in the terminal:

```bash
./gradlew clean build
```

### 5. Build and Run the App

Now you can build and run the app either on an emulator or a physical Android device.

---

## App Structure

- **MainActivity**: The main screen where users input a city or country and view the weather.
- **WeatherViewModel**: Handles the business logic and fetching of weather data.
- **WeatherRepository**: Interacts with the weather API to get data.
- **WeatherApiService**: Defines the Retrofit service interface to fetch weather data.

---

## Technologies Used

- **Jetpack Compose**: Modern UI toolkit for building native UIs.
- **Dagger & Hilt**: Dependency injection libraries to manage app dependencies.
- **Retrofit**: HTTP client for making network requests to the OpenWeatherMap API.
- **Kotlin Coroutines**: Used for asynchronous tasks like fetching data.

---

## Troubleshooting

- **Missing `secret.properties` file**: Ensure you’ve created the `secret.properties` file inside the `app` folder and added the correct API key.
- **API Key Issues**: If your API key is not working, you can generate a new one by visiting [OpenWeatherMap](https://openweathermap.org/).
- **Gradle Sync Issues**: If Gradle isn't syncing properly, try invalidating caches and restarting Android Studio by going to **File > Invalidate Caches / Restart**.

---

## License

This project is open-source and available under the [GPL-3.0 license](https://github.com/HephyHeatseeker/weatherOrNot?tab=GPL-3.0-1-ov-file#readme).

---

## Author

**Hephy**

Feel free to open issues or pull requests if you have any improvements or bug fixes!
