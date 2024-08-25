package com.demo.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Airports")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String airportCode;

    private String airportName;

    private String countryId;

    @PrePersist
    public  void generateAirportCode() {
        if (this.airportCode == null) {
            this.airportCode = generateCode();
        }

    }

    private String generateCode() {
        String prefix = "A";
        String formattedNumber = String.format("%04d", id);
        return prefix + formattedNumber;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
}

