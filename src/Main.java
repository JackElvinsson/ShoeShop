import Customer.Customer;
import DatabaseUtils.ReportTaskRepository;
import DatabaseUtils.Repository;
import Login.AdminLogin;
import Login.UserLogin;
import Shoe.Shoe;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    Main() throws IOException {

        Repository repository = new Repository();
        DataAccessObject DAO = new DataAccessObject();
        ReportTaskRepository reportTaskHandler = new ReportTaskRepository();

        List<Customer> customerList = DAO.getCustomerList();
        List<Shoe> shoeList = DAO.getShoeList();
        List<Customer> customerListPost = repository.getCustomerList();

        int shoeId = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("ÄR DU ADMIN? (J/N)");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("J")) {
            AdminLogin al = new AdminLogin();
        } else if (choice.equalsIgnoreCase("N")) {
            UserLogin ul = new UserLogin();
            customerListPost = ul.login(customerList);
            shoeId = ul.addToCart(shoeList);
            repository.addToCart(shoeId, customerListPost);
        } else {
            System.out.println("Ogiltigt val");
        }

//        reportTaskHandler.printCustomerOrderCount();
//        reportTaskHandler.printCustomerPaidTotal();
//        reportTaskHandler.printOrderValueByLocation();

//        UserLogin ul = new UserLogin();
//        customerListPost = ul.login(customerList);
//        shoeId = ul.addToCart(shoeList);
//        repository.addToCart(shoeId, customerListPost);
    }

    public static void main(String[] args) throws IOException {
        new Main();

    }
}


