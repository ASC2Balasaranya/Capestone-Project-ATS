package com.demo.service;

import com.demo.entity.Airport;
import com.demo.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public String generateAirportCode() {
        Long count = airportRepository.count();
        int nextNumber = count.intValue() + 1;
        return String.format("A%04d", nextNumber);
    }

    public Airport saveAirport(Airport airport) {
        airport.setAirportCode(generateAirportCode());
        return airportRepository.save(airport);
    }

  /*  public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }*/

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Optional<Airport> getAirportById(Long id) {
        return airportRepository.findById(id);
    }

    public Optional<Airport> getAirportByCode(String airportCode) {
        return Optional.ofNullable(airportRepository.findByAirportCode(airportCode));
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }
}
