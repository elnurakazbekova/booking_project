package com.iitu.booking.service;

import com.iitu.booking.model.Booking;
import com.iitu.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Booking addBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    public void delete(Booking booking){
        bookingRepository.delete(booking);
    }

}
