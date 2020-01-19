package com.wandell.springweather.model;

public class Weather {

    private String city;
    private String state;
    private final String summary;
    private final String icon;
    private final double temperature;
    private final double apparentTemperature;
    private final double humidity;
    private final double pressure;

    public Weather(String city, String state, String summary, String icon, double temperature, double apparentTemperature, double humidity, double pressure) {
        this.city = city;
        this.state = state;
        this.summary = summary;
        this.icon = icon;
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return summary;
    }

    public String getCity() {
        return city;
    }

    public String getSummary() {
        return summary;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public String getIcon() {
        return icon;
    }

    public String getState() {
        return state;
    }
}
