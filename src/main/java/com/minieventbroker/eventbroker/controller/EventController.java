package com.minieventbroker.eventbroker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
public class EventController {

    private List<Object> events = new ArrayList<>();
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("events")
    public ResponseEntity<Object> events(@RequestBody Object event){
        events.add(event);

        final String uri = "http://localhost:9092/events";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, event, Object.class);

        Iterator itr= events.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println("----------------------------");


        return new ResponseEntity<>(HttpStatus.OK);
    }
}



