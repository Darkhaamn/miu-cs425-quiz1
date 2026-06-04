package edu.miu.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Account {

    private long accountId;
    private String accountNumber;
    private String accountType;
    private LocalDate dateOpened;
    private BigDecimal balance;
    private Customer customer;

    public Account(
            long accountId,
            String accountNumber,
            String accountType,
            LocalDate dateOpened,
            BigDecimal balance,
            Customer customer
    ) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.dateOpened = dateOpened;
        this.balance = balance;
        this.customer = customer;
    }

    public AccountTier getTier() {
        int yearsOpened = Period.between(dateOpened, LocalDate.now()).getYears();

        if (yearsOpened >= 10 && balance.compareTo(new BigDecimal("100000.00")) >= 0) {
            return AccountTier.PLATINUM;
        }

        if (yearsOpened >= 5 && balance.compareTo(new BigDecimal("50000.00")) >= 0) {
            return AccountTier.GOLD;
        }

        if (yearsOpened >= 2 && balance.compareTo(new BigDecimal("10000.00")) >= 0) {
            return AccountTier.SILVER;
        }

        return AccountTier.STANDARD;
    }

    public long getAccountId() {
        return accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public LocalDate getDateOpened() {
        return dateOpened;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Customer getCustomer() {
        return customer;
    }
}