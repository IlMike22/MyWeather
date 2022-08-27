package com.mindmarket.weatherapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mindmarket.weatherapp.presentation.WeatherDataDisplay
import com.mindmarket.weatherapp.presentation.WeatherState
import com.mindmarket.weatherapp.R
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.currentWeatherData?.let { currentWeatherData ->
        Card(
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today ${
                        currentWeatherData.time.format(DateTimeFormatter.ofPattern("HH:mm"))
                    }",
                    modifier = Modifier.align(Alignment.End),
                    color = Color.White
                )
                Spacer(Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = currentWeatherData.weatherType.iconRes),
                    contentDescription = "weather icon",
                    modifier = Modifier.width(200.dp)
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "${currentWeatherData.temparatureCelsius}°C",
                    fontSize = 50.sp,
                    color = Color.White
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = currentWeatherData.weatherType.weatherDesc,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = currentWeatherData.pressure.roundToInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(
                            id = R.drawable.ic_pressure
                        ),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = currentWeatherData.humidity.roundToInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource(
                            id = R.drawable.ic_drop,
                        ),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = currentWeatherData.windSpeed.roundToInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(
                            id = R.drawable.ic_wind,
                        ),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                }
            }
        }
    }

}