package com.quartz.weather.model.repositories;

import com.quartz.weather.model.data_base_entities.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location,Integer> {
}
