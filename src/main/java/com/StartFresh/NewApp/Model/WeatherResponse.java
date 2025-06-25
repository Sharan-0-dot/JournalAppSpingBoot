package com.StartFresh.NewApp.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResponse {

    private Main main;

    @Data
    public class Main{
        private double temp;
        @JsonProperty("feels_like")
        private double feelsLike;
        private int pressure;
        private int humidity;
    }
}
