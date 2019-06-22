package com.quartz.weather.model.quartz;

import com.quartz.weather.model.data_base_entities.Location;
import com.quartz.weather.model.weather_details.WeatherDetail;
import org.quartz.*;

import java.sql.*;

public class QueryJob implements Job {
    WeatherDetail weatherDetail=new WeatherDetail();

    @Override
    public void execute(JobExecutionContext jobExecutionContext)  {

        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        Location location = (Location) jobDataMap.get("location");
        weatherDetail.getConnection(location.getName());
        weatherDetail.setTemperatureAndCityName();

        String url= "jdbc:mysql://localhost:3306/WeatherQuartz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username="Aleksandra";
        String password=null;

        try (Connection conn = DriverManager.getConnection(url, username, password)){
             Statement stmt = conn.createStatement();
             String SQL_ADD_MEASUREMENT="INSERT  INTO measurement(temperature,location_location_id) " +
                     "VALUES ('"+weatherDetail.getTemperature()+"','"+location.getLocation_id()+"') ";
             stmt.executeUpdate(SQL_ADD_MEASUREMENT);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
