package edu.miu;

import edu.miu.repository.AccountRepository;
import edu.miu.repository.CustomerRepository;
import edu.miu.service.AccountService;
import edu.miu.service.JsonReportService;

import java.util.Scanner;

public class CamsApplication {

    public static void main(String[] args) {
        CustomerRepository customerRepository = new CustomerRepository();
        AccountRepository accountRepository = new AccountRepository(customerRepository);

        AccountService accountService = new AccountService(accountRepository);
        JsonReportService jsonReportService = new JsonReportService(accountService);

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        System.out.println("==========================================");
        System.out.println(" CS425 Banking Corporation");
        System.out.println(" Customer-Accounts Management System");
        System.out.println("==========================================");

        while (running) {
            System.out.println();
            System.out.println("Menu:");
            System.out.println("1. Display all accounts in JSON format");
            System.out.println("2. Display Platinum tier accounts in JSON format");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.println();
                    System.out.println(jsonReportService.getAllAccountsJsonReport());
                }
                case "2" -> {
                    System.out.println();
                    System.out.println(jsonReportService.getPlatinumAccountsJsonReport());
                }
                case "3" -> {
                    running = false;
                    System.out.println("Thank you for using CAMS.");
                }
                default -> System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}