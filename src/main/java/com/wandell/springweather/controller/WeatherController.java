package com.wandell.springweather.controller;

import com.wandell.springweather.model.Weather;
import com.wandell.springweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @RequestMapping("/at/{city}")
    public ModelAndView getWeather(@PathVariable("city") String city) {
        Weather weather = weatherService.getCityWeather(city);
        return new ModelAndView("weather", "weather", weather);
    }
}
