package com.homework.testassignment.main.services;

import com.homework.testassignment.main.models.BookingTransaction;
import com.homework.testassignment.main.repositories.BookingTransactionRepository;
import com.homework.testassignment.main.utils.ExcelDataHelper;
import com.homework.testassignment.main.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BookingTransactionsService {
    @Autowired
    private BookingTransactionRepository bookingTransactionRepo;

    private Predicate<BookingTransaction> filterByTeam(String team) {
        Predicate<BookingTransaction> filter = b -> b.getTeam().equalsIgnoreCase(team);
        return filter;
    }

    private Predicate<BookingTransaction> filterByProduct(String product) {
        Predicate<BookingTransaction> filter = b -> b.getProduct().equalsIgnoreCase(product);
        return filter;
    }

    private Predicate<BookingTransaction> filterByType(String bookingType) {
        Predicate<BookingTransaction> filter = b -> b.getBookingType().equalsIgnoreCase(bookingType);
        return filter;
    }

    private Predicate<BookingTransaction> filterByStartDate(String startDate) {
        LocalDateTime filterDate = Utils.computeRequestDate(startDate);
        Predicate<BookingTransaction> filter = b -> (Utils.computeRequestDate(b.getBookingDate()).isAfter(filterDate)
                || filterDate.isEqual(Utils.computeRequestDate(b.getBookingDate())));
        return filter;
    }

    private Predicate<BookingTransaction> filterByEndDate(String endDate) {
        LocalDateTime filterDate = Utils.computeRequestDate(endDate);
        Predicate<BookingTransaction> filter = b -> (Utils.computeRequestDate(b.getBookingDate()).isBefore(filterDate)
                || filterDate.isEqual(Utils.computeRequestDate(b.getBookingDate())));
        return filter;
    }

    private Predicate<BookingTransaction> getFilterPredicate(Optional<String> team, Optional<String> product, Optional<String> bookingType, Optional<String> startDate, Optional<String> endDate) {
        Predicate<BookingTransaction> filter = b -> !b.getOpportunityID().isEmpty();
        if (team.isPresent()) {
            filter = filter.and(filterByTeam(team.get()));
        }
        if (product.isPresent()) {
            filter = filter.and(filterByProduct(product.get()));
        }
        if (bookingType.isPresent()) {
            filter = filter.and(filterByType(bookingType.get()));
        }
        if (startDate.isPresent()) {
            filter = filter.and(filterByStartDate(startDate.get()));
        }
        if (endDate.isPresent()) {
            filter = filter.and(filterByEndDate(endDate.get()));
        }
        return filter;
    }


    public void saveBookingTransactions(MultipartFile multipartFile, String sheet, String range) {
        try {
            List<BookingTransaction> bookingTransactions = ExcelDataHelper.excelToBookingTransactions(multipartFile, sheet, range);
            bookingTransactionRepo.saveAll(bookingTransactions);
        } catch (IOException e) {
            throw new RuntimeException("Fail to save file data : " + e.getMessage());
        }
    }

    public List<BookingTransaction> getAllServices(Optional<String> team, Optional<String> product, Optional<String> bookingType, Optional<String> startDate, Optional<String> endDate) {
        List<BookingTransaction> bookingList = new ArrayList<>();
        bookingTransactionRepo.findAll().forEach(bookingList::add);
        bookingList = bookingList.stream().filter(getFilterPredicate(team, product, bookingType, startDate, endDate)).collect(Collectors.toList());
        return bookingList;
    }
}
