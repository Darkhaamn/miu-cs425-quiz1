package edu.miu.repository;

import edu.miu.domain.Account;
import edu.miu.domain.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private final List<Account> accounts = new ArrayList<>();

    public AccountRepository(CustomerRepository customerRepository) {
        loadSampleData(customerRepository);
    }

    private void loadSampleData(CustomerRepository customerRepository) {
        Customer bob = customerRepository.findById(1);
        Customer anna = customerRepository.findById(2);
        Customer carlos = customerRepository.findById(3);

        accounts.add(new Account(
                1,
                "AC1002",
                "Checking",
                LocalDate.of(2016, 5, 17),
                new BigDecimal("155900.50"),
                bob
        ));

        accounts.add(new Account(
                2,
                "AS1001",
                "Savings",
                LocalDate.of(2021, 6, 2),
                new BigDecimal("12500.95"),
                bob
        ));

        accounts.add(new Account(
                3,
                "AS1003",
                "Savings",
                LocalDate.of(2016, 7, 11),
                new BigDecimal("75000.00"),
                carlos
        ));

        accounts.add(new Account(
                4,
                "AC1004",
                "Checking",
                LocalDate.of(2024, 3, 29),
                new BigDecimal("11700.99"),
                anna
        ));
    }

    public List<Account> findAll() {
        return accounts;
    }
}