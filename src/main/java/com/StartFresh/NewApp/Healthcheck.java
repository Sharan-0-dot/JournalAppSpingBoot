package com.StartFresh.NewApp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Healthcheck {

    @GetMapping("/")
    public String check(HttpServletRequest request) {
        return "The Server is Running Perfectly alright reach the specific endpoint for service "+request.getSession().getId();
    }
}
