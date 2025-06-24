package com.StartFresh.NewApp.Controller;

import com.StartFresh.NewApp.Model.User;
import com.StartFresh.NewApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService service;

    @PostMapping("/createUser/")
    public ResponseEntity<?> postUser(@RequestBody User u) {
        return new ResponseEntity<>(service.postNewUser(u), HttpStatus.OK);
    }
}
