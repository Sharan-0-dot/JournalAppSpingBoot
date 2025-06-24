package com.StartFresh.NewApp.Controller;


import com.StartFresh.NewApp.Model.User;
import com.StartFresh.NewApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public ResponseEntity<?> findUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(service.findByUserName(authentication.getName()), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> putUser(@RequestBody User u) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User u1 = service.findByUserName(name);
        u1.setUsername(u.getUsername());
        u1.setPassword(u.getPassword());
        return new ResponseEntity<>(service.postUser(u1),HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User u1 = service.findByUserName(name);
        service.deleteById(u1.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
