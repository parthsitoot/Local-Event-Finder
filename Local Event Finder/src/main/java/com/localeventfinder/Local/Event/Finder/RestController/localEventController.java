package com.localeventfinder.Local.Event.Finder.RestController;

import com.localeventfinder.Local.Event.Finder.Entity.User;
import com.localeventfinder.Local.Event.Finder.Entity.Variables;
import com.localeventfinder.Local.Event.Finder.Services.User_services;
import com.localeventfinder.Local.Event.Finder.Services.services;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/localEvent")
public class localEventController {
    @Autowired
    private services service;

    @Autowired
    private User_services userServices;

    @PostMapping("/saveEntry/{username}")
    public ResponseEntity<?> save(@RequestBody Variables var, @PathVariable String username) {
        try {
            service.saveEntry(var, username);
            return new ResponseEntity<>(var, HttpStatus.CREATED);

        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Variables>> getAll(@PathVariable String username) {
        User user = userServices.findByuserName(username);
        if(user != null) {
            return new ResponseEntity<>(user.getVariables(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Variables> findById(@PathVariable ObjectId id) {
        Variables var = service.findById(id).orElse(null);
        if(var != null) {
            return new ResponseEntity<>(var, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Variables> deleteById(@PathVariable ObjectId id) {
        Variables var = service.deleteById(id).orElse(null);
        if(var != null) {
            return new ResponseEntity<>(var, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Variables var, @PathVariable ObjectId id) {
        Variables old = findById(id).getBody();
        if(old == null) {
            return new ResponseEntity<>("Id not present in database to update", HttpStatus.NOT_FOUND);
        }
        old.setDate(LocalDateTime.now());
        if(var.getVenue() != null || var.getVenue().equals("")) {
            var.setVenue(old.getVenue());
        }
        if(var.getDetails() != null || var.getDetails().equals("")) {
            var.setDetails(old.getDetails());
        }
        if(var.getTime() != null || var.getTime().equals("")) {
            var.setTime(old.getTime());
        }
        //service.saveEntry(var, username);
        return new ResponseEntity<>(var, HttpStatus.OK);
    }
}