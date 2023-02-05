package Login;

import DatabaseUtils.ReportTaskRepository;

import java.util.Scanner;

public class AdminLogin {

    public AdminLogin () {

        ReportTaskRepository reportTaskHandler = new ReportTaskRepository();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Välkommen till adminsidan");
            System.out.println("Välj ett alternativ");
            System.out.println("1. Skriv ut rapport");
            System.out.println("2. Logga ut");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> printReport(reportTaskHandler);
                case 2 -> {
                    System.out.println("Du har loggat ut");
                    return;
                }
                default -> System.out.println("Ogiltigt val");
            }
        }
    }

    private void printReport(ReportTaskRepository reportTaskHandler) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vilken rapport vill du skriva ut?");
        System.out.println("1. Kundorderantal");
        System.out.println("2. Kundorderbelopp");
        System.out.println("3. Total försäljning enligt ort");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> reportTaskHandler.printCustomerOrderCount();
            case 2 -> reportTaskHandler.printCustomerPaidTotal();
            case 3 -> reportTaskHandler.printOrderValueByLocation();
            default -> System.out.println("Ogiltigt val");
        }
    }
}
