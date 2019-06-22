package com.quartz.weather.model.data_base_entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="measurement")
public class Measurement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer measurement_id;
    private Integer temperature;
    @ManyToOne
    private Location location;


   // getters, setters, constr.
    public Measurement(Location location) {
        this.location = location;
    }

    public Measurement() {
    }

    public Measurement(Integer temperature, Location location) {
        this.temperature = temperature;
        this.location = location;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getMeasurement_id() {
        return measurement_id;
    }

    public void setMeasurement_id(Integer measurement_id) {
        this.measurement_id = measurement_id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
