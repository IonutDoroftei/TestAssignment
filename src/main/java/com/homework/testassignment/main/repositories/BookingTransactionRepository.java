package com.homework.testassignment.main.repositories;


import com.homework.testassignment.main.models.BookingTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingTransactionRepository extends CrudRepository<BookingTransaction, Long> {

}
