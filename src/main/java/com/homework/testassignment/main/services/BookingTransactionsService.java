package com.homework.testassignment.main.services;

import com.homework.testassignment.main.models.Booking;
import com.homework.testassignment.main.repositories.BookingTransactionRepository;
import com.homework.testassignment.main.utils.BookingSpecification;
import com.homework.testassignment.main.utils.ExcelDataHelper;
import com.homework.testassignment.main.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
public class BookingTransactionsService {
    @Autowired
    private BookingTransactionRepository bookingTransactionRepo;

    public void saveBookingTransactions(MultipartFile multipartFile, String sheet, String range) {
        try {
            List<Booking> bookings = ExcelDataHelper.excelToBookingTransactions(multipartFile, sheet, range).stream()
                    .filter(booking -> !bookingTransactionRepo.findByOpportunityID(booking.getOpportunityID()).isPresent()).collect(Collectors.toList());
            for (Booking booking : bookings) {
                bookingTransactionRepo.save(booking);
            }
        } catch (IOException e) {
            throw new RuntimeException("Fail to save file data : " + e.getMessage());
        }
    }

    public List<Booking> getAllServices(Optional<String> team, Optional<String> product, Optional<String> bookingType, Optional<String> startDate, Optional<String> endDate) {
        BookingSpecification teamSpec = new BookingSpecification(new SearchCriteria(team.orElse(""), "team", team.isPresent()));
        BookingSpecification productSpec = new BookingSpecification(new SearchCriteria(product.orElse(""), "product", product.isPresent()));
        BookingSpecification bookingTypeSpec = new BookingSpecification(new SearchCriteria(bookingType.orElse(""), "bookingType", bookingType.isPresent()));
        BookingSpecification dateSpec = new BookingSpecification(new SearchCriteria(startDate.orElse(""), endDate.orElse(""), "bookingDate", startDate.isPresent(), true));
        return bookingTransactionRepo.findAll(Specification.where(teamSpec).and(productSpec).and(bookingTypeSpec).and(dateSpec));
    }
}
