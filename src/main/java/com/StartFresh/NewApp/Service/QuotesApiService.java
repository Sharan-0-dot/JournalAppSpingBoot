package com.StartFresh.NewApp.Service;

import com.StartFresh.NewApp.Model.QuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QuotesApiService {

    private static final String API = "https://api.api-ninjas.com/v1/quotes";
    private static final String apiKey = "kdhKuf2md4ST3WZskumXXw==v0XkedTCRpt7Yk5g";

    @Autowired
    private RestTemplate restTemplate;

    public QuoteResponse[] getQuote() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<QuoteResponse[]> response =  restTemplate.exchange(API, HttpMethod.GET, entity, QuoteResponse[].class);
        return response.getBody();
    }
}
