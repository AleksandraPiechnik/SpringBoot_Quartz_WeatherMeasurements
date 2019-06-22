package com.quartz.weather.model.repositories;

import com.quartz.weather.model.data_base_entities.Measurement;
import org.springframework.data.repository.CrudRepository;

public interface MeasurementRepository extends CrudRepository<Measurement,Integer> {
}
