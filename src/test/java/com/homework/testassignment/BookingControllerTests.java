package com.homework.testassignment;


import com.homework.testassignment.main.models.Booking;
import com.homework.testassignment.main.services.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookingService bookingService;

    @Test
    public void testLoadOpportunities() throws Exception {
        Booking booking = BookingServiceTests.getBookingMock();
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        doReturn(bookings).when(bookingService).getAllBookings(Optional.of(booking.getTeam()), Optional.of(booking.getProduct()), Optional.of(booking.getBookingType()),
                Optional.of("1/30/2018"), Optional.of("12/12/2018"));
        mockMvc.perform(get("/booking/opportunity/"))
                .andExpect(status().isOk());
    }
}
