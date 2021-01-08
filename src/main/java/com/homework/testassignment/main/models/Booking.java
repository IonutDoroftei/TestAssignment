package com.homework.testassignment.main.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BookingTransactions")
public class Booking {
    @Id
    private String opportunityID;
    private String customerName;
    private Long bookingDate;
    private String bookingType;
    private Double total;
    private String accountExecutive;
    private String salesOrganization;
    private String team;
    private String product;
    private boolean renewable;
}

