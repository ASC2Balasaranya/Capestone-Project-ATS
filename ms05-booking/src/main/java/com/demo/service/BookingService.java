package com.demo.service;

import com.demo.entity.Booking;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    /*public String generateAirportCode() {
        Long count = bookingRepository.count();
        int nextNumber = count.intValue() + 1;
        return String.format("B%04d", nextNumber);
    }

    public Booking saveAirport(Booking booking) {
        booking.setBookingId(generateAirportCode());
        return bookingRepository.save(booking);
    }*/

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void deleteBooking(String bookingId) {
        Booking booking = bookingRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        bookingRepository.delete(booking);
    }
}

