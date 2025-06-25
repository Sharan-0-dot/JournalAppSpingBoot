package com.StartFresh.NewApp.Service;


import com.StartFresh.NewApp.Model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApiService {

    private static final String API = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=f4ce1512eaaf4abdf4f4fcef10c217b7";
    private static final String apiKey = "f4ce1512eaaf4abdf4f4fcef10c217b7";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather() {
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(API, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
