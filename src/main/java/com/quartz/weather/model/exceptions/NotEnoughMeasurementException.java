package com.quartz.weather.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotEnoughMeasurementException extends Exception {
    public NotEnoughMeasurementException(String message) {
        super(message);
    }
}
