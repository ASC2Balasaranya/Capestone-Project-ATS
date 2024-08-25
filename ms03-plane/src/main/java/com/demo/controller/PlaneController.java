package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.entity.Plane;
import com.demo.service.PlaneService;

@RestController
@RequestMapping("/")
public class PlaneController {

    @Autowired
    private PlaneService planeService;

    @PostMapping("/addplane")
    public ResponseEntity<Plane> createPlane(@RequestBody Plane plane) {
        try {
            Plane createdPlane = planeService.createPlane(plane);
            return new ResponseEntity<>(createdPlane, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
