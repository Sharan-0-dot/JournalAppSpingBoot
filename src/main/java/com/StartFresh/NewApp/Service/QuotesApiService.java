package com.StartFresh.NewApp.Service;

import com.StartFresh.NewApp.Cache.AppCache;
import com.StartFresh.NewApp.Model.QuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QuotesApiService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache cache;


    public QuoteResponse[] getQuote() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<QuoteResponse[]> response =  restTemplate.exchange(cache.appCache.get("quoteAPI"), HttpMethod.GET, entity, QuoteResponse[].class);
        return response.getBody();
    }
}
