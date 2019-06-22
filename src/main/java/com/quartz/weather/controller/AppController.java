package com.quartz.weather.controller;

import com.quartz.weather.model.data_base_entities.Location;
import com.quartz.weather.model.exceptions.LocationNotFoundException;
import com.quartz.weather.model.quartz.WeatherScheduler;
import com.quartz.weather.model.repositories.LocationRepository;
import com.quartz.weather.model.services.LocationService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppController {

    @Autowired
    private LocationRepository locationRepository;

    private WeatherScheduler weatherScheduler=new WeatherScheduler();


    public LocationService locationService = new LocationService();

    public AppController() throws SchedulerException {
    }

    @RequestMapping(value = "/api/register/{city_name}/{frequency}", method = RequestMethod.POST)
    public Location addLocation(@PathVariable String city_name, @PathVariable String frequency) throws LocationNotFoundException, SchedulerException {

        Location location = locationService.addLocation(city_name);
        if (location == null) throw new LocationNotFoundException("Can't find city name");
        else {
            locationRepository.save(location);
            weatherScheduler.addJob(location, Integer.parseInt(frequency));

            return location;
        }
    }


}
