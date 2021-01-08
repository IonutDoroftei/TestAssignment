package com.homework.testassignment;

import com.homework.testassignment.main.models.Booking;
import com.homework.testassignment.main.repositories.BookingRepository;
import com.homework.testassignment.main.services.BookingService;

import com.homework.testassignment.main.utils.BookingSpecification;
import com.homework.testassignment.main.utils.SearchCriteria;
import com.homework.testassignment.main.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookingServiceTests {
    @InjectMocks
    private BookingService bookingService;
    @Mock
    private BookingRepository bookingRepo;

    @BeforeEach
    void setUp() {
        List<Booking> bookings = new ArrayList<>();
        Booking booking = getBookingMock();
        bookings.add(booking);
        BookingSpecification teamSpec = new BookingSpecification(new SearchCriteria(booking.getTeam(), "team", true));
        BookingSpecification productSpec = new BookingSpecification(new SearchCriteria(booking.getProduct(), "product", true));
        BookingSpecification bookingTypeSpec = new BookingSpecification(new SearchCriteria(booking.getBookingType(), "bookingType", true));
        BookingSpecification betweenDateSpec = new BookingSpecification(new SearchCriteria("1/30/2018", "12/12/2018", "bookingDate", true, true));
        when(bookingRepo.findAll(teamSpec.and(productSpec).and(bookingTypeSpec).and(betweenDateSpec))).thenReturn(bookings);
    }

    @Test
    public void testGetAllBookings() {
        Booking booking = getBookingMock();
        List<Booking> savedBookings = bookingService.getAllBookings(Optional.of(booking.getTeam()), Optional.of(booking.getProduct()), Optional.of(booking.getBookingType()),
                Optional.of("1/30/2018"), Optional.of("12/12/2018"));
        assertThat(savedBookings.size() > 0);
    }

    public static Booking getBookingMock() {
        Booking booking = new Booking();
        booking.setOpportunityID("6de40407-3e0f-4792-a851-45cec0f2db2f");
        booking.setTeam("EAST");
        booking.setProduct("ALLOY");
        booking.setTotal(365005.39);
        booking.setBookingType("new");
        booking.setAccountExecutive("Ada Dries");
        booking.setBookingDate(Utils.computeRequestDate("3/30/2018"));
        booking.setCustomerName("Linkbuzz");
        booking.setRenewable(Utils.isRenewable("YES"));
        booking.setSalesOrganization("Mid-Market");
        return booking;
    }
}
