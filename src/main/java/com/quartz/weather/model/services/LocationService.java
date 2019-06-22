package com.quartz.weather.model.services;


import com.quartz.weather.model.data_base_entities.Location;
import com.quartz.weather.model.repositories.LocationRepository;
import com.quartz.weather.model.weather_details.WeatherDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LocationService {


    @Autowired
    public LocationRepository locationRepository;

    public Location addLocation(String city_name) {
        WeatherDetail weatherDetail = new WeatherDetail();
        weatherDetail.getConnection(city_name);
        weatherDetail.setTemperatureAndCityName();
        if (weatherDetail.getCity_name() != null) {
            Location location = new Location(city_name);
            return location;
        } else return null;
    }
}
