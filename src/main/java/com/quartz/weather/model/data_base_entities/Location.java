package com.quartz.weather.model.data_base_entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="location")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer location_id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location" )
    private Set<Measurement> measurementSet= new HashSet<>();

    public Location(String name) {
        this.name = name;
    }

    //getters, setters, constr.
    public Location() {
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Measurement> getMeasurementSet() {
        return measurementSet;
    }

    public void setMeasurementSet(Set<Measurement> measurementSet) {
        this.measurementSet = measurementSet;
    }

}
