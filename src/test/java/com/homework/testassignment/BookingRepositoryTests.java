package com.homework.testassignment;

import com.homework.testassignment.main.models.Booking;
import com.homework.testassignment.main.repositories.BookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookingRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BookingRepository bookingRepo;

    @Test
    public void testFindByOpportunity() {
        Booking booking = BookingServiceTests.getBookingMock();
        entityManager.persistAndFlush(booking);
        Optional<Booking> searchedBooking = bookingRepo.findByOpportunityID(booking.getOpportunityID());
        assertThat(searchedBooking.isPresent());
    }
}
