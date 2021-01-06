package com.homework.testassignment.main.models;

import com.homework.testassignment.main.utils.Utils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BookingTransactions")
public class BookingTransaction {
    @Id
    private String opportunityID;
    private String customerName;
    private String bookingDate;
    private String bookingType;
    private Double total;
    private String accountExecutive;
    private String salesOrganization;
    private String team;
    private String product;
    private boolean renewable;

    public BookingTransaction() {
        //empty constructor
    }

    public BookingTransaction(String opportunityID, String customerName, String bookingDate, String bookingType,
                              Double total, String accountExecutive, String salesOrganization, String team, String product, String renewable) {
        this.opportunityID = opportunityID;
        this.customerName = customerName;
        this.bookingDate = bookingDate;
        this.bookingType = bookingType;
        this.total = total;
        this.accountExecutive = accountExecutive;
        this.salesOrganization = salesOrganization;
        this.team = team;
        this.product = product;
        this.renewable = Utils.isRenewable(renewable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingTransaction that = (BookingTransaction) o;
        return renewable == that.renewable &&
                Objects.equals(opportunityID, that.opportunityID) &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(bookingDate, that.bookingDate) &&
                Objects.equals(bookingType, that.bookingType) &&
                Objects.equals(total, that.total) &&
                Objects.equals(accountExecutive, that.accountExecutive) &&
                Objects.equals(salesOrganization, that.salesOrganization) &&
                Objects.equals(team, that.team) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opportunityID, customerName, bookingDate, bookingType, total, accountExecutive, salesOrganization, team, product, renewable);
    }

    @Override
    public String toString() {
        return "BookingTransaction{" +
                "opportunityID='" + opportunityID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", bookingDate='" + bookingDate + '\'' +
                ", bookingType='" + bookingType + '\'' +
                ", total='" + total + '\'' +
                ", accountExecutive='" + accountExecutive + '\'' +
                ", salesOrganization='" + salesOrganization + '\'' +
                ", team='" + team + '\'' +
                ", product='" + product + '\'' +
                ", renewable=" + renewable +
                '}';
    }

    public String getOpportunityID() {
        return opportunityID;
    }

    public void setOpportunityID(String opportunityID) {
        this.opportunityID = opportunityID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getAccountExecutive() {
        return accountExecutive;
    }

    public void setAccountExecutive(String accountExecutive) {
        this.accountExecutive = accountExecutive;
    }

    public String getSalesOrganization() {
        return salesOrganization;
    }

    public void setSalesOrganization(String salesOrganization) {
        this.salesOrganization = salesOrganization;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public boolean isRenewable() {
        return renewable;
    }

    public void setRenewable(boolean renewable) {
        this.renewable = renewable;
    }
}

