package com.wandell.springweather.service;

import com.wandell.springweather.model.Weather;

public interface WeatherService {
    Weather getCityWeather(String city);
}
