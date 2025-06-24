package com.StartFresh.NewApp.Controller;

import com.StartFresh.NewApp.Model.Journal;
import com.StartFresh.NewApp.Model.User;
import com.StartFresh.NewApp.Service.JournalEntryService;
import com.StartFresh.NewApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService service;

    @Autowired
    private UserService userService;


    @GetMapping("/{myId}")
    public ResponseEntity<?> getById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if(!service.isPresent(name, myId)) {
            return new ResponseEntity<>("The specified journal does not belong to user : "+name, HttpStatus.NOT_FOUND);
        }
        Journal j = service.getById(myId);
        if(j != null) {
            return new ResponseEntity<>(j, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllEntriesOfUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User u = userService.findByUserName(name);
        List<Journal> all = u.getJournalEntries();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/")
    public ResponseEntity<?> postJournalOfUser(@RequestBody Journal j) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return new ResponseEntity<>(service.postJournalOfUser(j, name), HttpStatus.OK);
    }

    @PutMapping("/{myId}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId myId, @RequestBody Journal j) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if(j == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(!service.isPresent(name, myId)) {
            return new ResponseEntity<>("The specified journal does not belong to user : "+name+" or not present", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.putJournal(name, myId, j), HttpStatus.OK);
    }

    @DeleteMapping("/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if(!service.isPresent(name, myId)) {
            return new ResponseEntity<>("The specified journal does not belong to user : "+name+" or not present", HttpStatus.NOT_FOUND);
        }
        Journal j = service.deleteById(myId, name);
        if(j == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(j, HttpStatus.OK);
    }

}
