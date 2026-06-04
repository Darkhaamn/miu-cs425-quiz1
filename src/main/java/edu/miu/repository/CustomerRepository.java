package edu.miu.repository;

import edu.miu.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private final List<Customer> customers = new ArrayList<>();

    public CustomerRepository() {
        loadSampleData();
    }

    private void loadSampleData() {
        customers.add(new Customer(1, "Bob", "Jones"));
        customers.add(new Customer(2, "Anna", "Smith"));
        customers.add(new Customer(3, "Carlos", "Jimenez"));
    }

    public List<Customer> findAll() {
        return customers;
    }

    public Customer findById(int customerId) {
        return customers.stream()
                .filter(customer -> customer.getCustomerId() == customerId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
    }
}