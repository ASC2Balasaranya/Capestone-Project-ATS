package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.entity.Plane;
import com.demo.repository.PlaneRepository;

@Service
public class PlaneServiceImpl implements PlaneService {

    @Autowired
    private PlaneRepository planeRepository;

    @Override
    public Plane createPlane(Plane plane) {
        String planeCode = generatePlaneCode();
        plane.setPlaneCode(planeCode);
        return planeRepository.save(plane);
    }

    private String generatePlaneCode() {
        Plane lastPlane = planeRepository.findTopByOrderByIdDesc();
        int nextId = (lastPlane != null) ? lastPlane.getId().intValue() + 1 : 1;
        return String.format("P%04d", nextId);
    }
}
