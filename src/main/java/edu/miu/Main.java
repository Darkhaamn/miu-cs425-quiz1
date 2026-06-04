package edu.miu;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Customer> customers = loadCustomers();
        List<Account> accounts = loadAccounts(customers);

        System.out.println("===== All Accounts JSON =====");
        System.out.println(toAllAccountsJson(accounts));

        System.out.println("\n===== Platinum Accounts JSON =====");
        System.out.println(toPlatinumAccountsJson(accounts));
    }

    private static List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer(1, "Bob", "Jones"));
        customers.add(new Customer(2, "Anna", "Smith"));
        customers.add(new Customer(3, "Carlos", "Jimenez"));

        return customers;
    }

    private static List<Account> loadAccounts(List<Customer> customers) {
        List<Account> accounts = new ArrayList<>();

        Customer bob = findCustomerById(customers, 1);
        Customer anna = findCustomerById(customers, 2);
        Customer carlos = findCustomerById(customers, 3);

        accounts.add(new Account(
                1,
                "AC1002",
                "Checking",
                LocalDate.of(2026, 5, 17),
                new BigDecimal("155900.50"),
                bob));

        accounts.add(new Account(
                2,
                "AS1001",
                "Savings",
                LocalDate.of(2023, 6, 2),
                new BigDecimal("12500.95"),
                bob));

        accounts.add(new Account(
                3,
                "AS1003",
                "Savings",
                LocalDate.of(2010, 7, 11),
                new BigDecimal("1075000.00"),
                carlos));

        accounts.add(new Account(
                4,
                "AC1004",
                "Checking",
                LocalDate.of(2024, 3, 29),
                new BigDecimal("11700.99"),
                anna));

        return accounts;
    }

    private static Customer findCustomerById(List<Customer> customers, int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    private static String toAllAccountsJson(List<Account> accounts) {
        List<Account> sortedAccounts = accounts.stream()
                .sorted(Comparator.comparing(Account::getBalance).reversed())
                .toList();

        BigDecimal liquidityPosition = accounts.stream()
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        StringBuilder json = new StringBuilder();

        json.append("{\n");
        json.append("  \"accounts\": [\n");

        for (int i = 0; i < sortedAccounts.size(); i++) {
            Account account = sortedAccounts.get(i);
            json.append(accountToJson(account, 4));

            if (i < sortedAccounts.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("  ],\n");
        json.append("  \"liquidityPosition\": ")
                .append(liquidityPosition)
                .append("\n");
        json.append("}");

        return json.toString();
    }

    private static String toPlatinumAccountsJson(List<Account> accounts) {
        List<Account> platinumAccounts = accounts.stream()
                .filter(account -> account.getTier().equals("Platinum"))
                .sorted(Comparator.comparing(Account::getBalance).reversed())
                .toList();

        StringBuilder json = new StringBuilder();

        json.append("{\n");
        json.append("  \"platinumAccounts\": [\n");

        for (int i = 0; i < platinumAccounts.size(); i++) {
            Account account = platinumAccounts.get(i);
            json.append(accountToJson(account, 4));

            if (i < platinumAccounts.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("  ]\n");
        json.append("}");

        return json.toString();
    }

    private static String accountToJson(Account account, int spaces) {
        String indent = " ".repeat(spaces);
        String childIndent = " ".repeat(spaces + 2);

        Customer customer = account.getCustomer();

        return indent + "{\n" +
                childIndent + "\"accountId\": " + account.getAccountId() + ",\n" +
                childIndent + "\"accountNumber\": \"" + account.getAccountNumber() + "\",\n" +
                childIndent + "\"accountType\": \"" + account.getAccountType() + "\",\n" +
                childIndent + "\"dateOpened\": \"" + account.getDateOpened() + "\",\n" +
                childIndent + "\"balance\": " + account.getBalance() + ",\n" +
                childIndent + "\"tier\": \"" + account.getTier() + "\",\n" +
                childIndent + "\"customer\": {\n" +
                childIndent + "  \"customerId\": " + customer.getCustomerId() + ",\n" +
                childIndent + "  \"firstName\": \"" + customer.getFirstName() + "\",\n" +
                childIndent + "  \"lastName\": \"" + customer.getLastName() + "\"\n" +
                childIndent + "}\n" +
                indent + "}";
    }
}
