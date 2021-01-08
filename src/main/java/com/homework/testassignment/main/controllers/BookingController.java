package com.homework.testassignment.main.controllers;

import com.homework.testassignment.main.models.Booking;
import com.homework.testassignment.main.services.BookingTransactionsService;
import com.homework.testassignment.main.services.FileInfoService;
import com.homework.testassignment.main.utils.ExcelDataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingTransactionsService bookingTransactionService;
    @Autowired
    private FileInfoService fileInfoService;

    @GetMapping("/opportunity")
    public ResponseEntity<List<Booking>> loadOpportunity(@RequestParam Optional<String> team,
                                                         @RequestParam Optional<String> product,
                                                         @RequestParam Optional<String> bookingType,
                                                         @RequestParam Optional<String> startDate,
                                                         @RequestParam Optional<String> endDate) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingTransactionService.getAllServices(team, product, bookingType, startDate, endDate));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile,
                                         @RequestParam("sheet") String sheet,
                                         @RequestParam("range") String range) {
        if (ExcelDataHelper.hasExcelFormat(multipartFile))
            try {
                bookingTransactionService.saveBookingTransactions(multipartFile, sheet, range);
                fileInfoService.saveFileMetadata(multipartFile);
                return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File upload fails please check again sheet name or data range.");
            }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong file format!");
    }
}
