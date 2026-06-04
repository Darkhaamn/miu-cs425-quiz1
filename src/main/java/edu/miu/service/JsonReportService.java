package edu.miu.service;

import edu.miu.domain.Account;
import edu.miu.domain.Customer;

import java.math.BigDecimal;
import java.util.List;

public class JsonReportService {

    private final AccountService accountService;

    public JsonReportService(AccountService accountService) {
        this.accountService = accountService;
    }

    public String getAllAccountsJsonReport() {
        List<Account> accounts = accountService.getAllAccountsSortedByBalanceDesc();
        BigDecimal liquidityPosition = accountService.calculateLiquidityPosition();

        StringBuilder json = new StringBuilder();

        json.append("{\n");
        json.append("  \"accounts\": [\n");

        for (int i = 0; i < accounts.size(); i++) {
            json.append(accountToJson(accounts.get(i)));

            if (i < accounts.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("  ],\n");
        json.append("  \"liquidityPosition\": ").append(liquidityPosition).append("\n");
        json.append("}");

        return json.toString();
    }

    public String getPlatinumAccountsJsonReport() {
        List<Account> accounts = accountService.getPlatinumAccountsSortedByBalanceDesc();

        StringBuilder json = new StringBuilder();

        json.append("{\n");
        json.append("  \"platinumAccounts\": [\n");

        for (int i = 0; i < accounts.size(); i++) {
            json.append(accountToJson(accounts.get(i)));

            if (i < accounts.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("  ]\n");
        json.append("}");

        return json.toString();
    }

    private String accountToJson(Account account) {
        Customer customer = account.getCustomer();

        return "    {\n" +
                "      \"accountId\": " + account.getAccountId() + ",\n" +
                "      \"accountNumber\": \"" + account.getAccountNumber() + "\",\n" +
                "      \"accountType\": \"" + account.getAccountType() + "\",\n" +
                "      \"dateOpened\": \"" + account.getDateOpened() + "\",\n" +
                "      \"balance\": " + account.getBalance() + ",\n" +
                "      \"tier\": \"" + account.getTier() + "\",\n" +
                "      \"customer\": {\n" +
                "        \"customerId\": " + customer.getCustomerId() + ",\n" +
                "        \"firstName\": \"" + customer.getFirstName() + "\",\n" +
                "        \"lastName\": \"" + customer.getLastName() + "\"\n" +
                "      }\n" +
                "    }";
    }
}