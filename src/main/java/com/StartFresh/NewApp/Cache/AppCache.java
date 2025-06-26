package com.StartFresh.NewApp.Cache;

import com.StartFresh.NewApp.Model.QuoteAPIConfig;
import com.StartFresh.NewApp.Repository.QuoteApiConfigRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;

@Component
public class AppCache {

    @Autowired
    private QuoteApiConfigRepo configRepo;

    public HashMap<String, String> appCache;

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<QuoteAPIConfig> all = configRepo.findAll();
        for(QuoteAPIConfig api : all) {
            this.appCache.put(api.getKey(), api.getValue());
        }
    }
}
