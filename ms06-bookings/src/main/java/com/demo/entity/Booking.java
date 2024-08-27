package com.demo.entity;


import javax.persistence.*;
import java.util.Date;

@Entity

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 5)
    private String bookingId;

    @Column(nullable = false)
    private String passengerId;

    @Column(nullable = false)
    private String scheduleId;

    @Column(nullable = false)
    private Date bookingDate;

    @Column(nullable = false)
    private int seatNumber;

    @Column(nullable = false)
    private double ticketCost;

    @Column(nullable = false)
    private double totalAmount;

//    @PrePersist
//    public  void generateAirportCode() {
//        if (this.bookingId== null) {
//            this.bookingId = generateCode();
//        }
//
//    }

    private String generateCode() {
        String prefix = "B";
        String formattedNumber = String.format("%04d", id);
        return prefix + formattedNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(double ticketCost) {
        this.ticketCost = ticketCost;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
