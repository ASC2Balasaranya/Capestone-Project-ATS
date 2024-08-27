package com.demo.proxy;

import com.demo.model.BookingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("ms06-bookings")
public interface BookingFeignClient {
    @GetMapping("/show")
     List<BookingDTO> getAllBookings();

}
