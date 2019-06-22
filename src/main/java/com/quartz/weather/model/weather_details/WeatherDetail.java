package com.quartz.weather.model.weather_details;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class WeatherDetail {

    private String key="d372c6d8709fea08fc58f4b1c702f812";
    private HttpURLConnection connection;
    private BufferedReader reader;
    private String line;
    private StringBuffer responseContent= new StringBuffer();
    private Integer temperature=null;
    private String city_name=null;

    public void getConnection(String city_name){
        try {

            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city_name+"&appid="+key);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) { responseContent.append(line); }
                reader.close();

            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) { responseContent.append(line); }
                reader.close();

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("WeatherDetail connection problem occurred");
        }
        finally {
            connection.disconnect();
        }
    }
    public void setTemperatureAndCityName(){
        JSONObject jo= new JSONObject(responseContent.toString());
        JSONObject main= jo.getJSONObject("main");
        temperature= main.getInt("temp");
        city_name= jo.getString("name");
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
