package com.StartFresh.NewApp.Controller;

import com.StartFresh.NewApp.Model.User;
import com.StartFresh.NewApp.Repository.UserRepositoryImpl;
import com.StartFresh.NewApp.Service.QuotesApiService;
import com.StartFresh.NewApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepositoryImpl userRepository;


    @Autowired
    private QuotesApiService quotesApiService;

    @PostMapping("/createUser/")
    public ResponseEntity<?> postUser(@RequestBody User u) {
        return new ResponseEntity<>(service.postNewUser(u), HttpStatus.OK);
    }

    @GetMapping("/quote")
    public ResponseEntity<?> getQuote() {
        return new ResponseEntity<>(quotesApiService.getQuote(), HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public  ResponseEntity<?> getuser() {
        return new ResponseEntity<>(userRepository.getUserForSA(), HttpStatus.OK);
    }
}
