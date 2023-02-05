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

    Repository repository;
    DataAccessObject DAO;
    ReportTaskRepository reportTaskHandler;
    List<Customer> customerList;
    List<Shoe> shoeList;
    List<Customer> customerListPost;


    Main() throws IOException {

        while (true) {
            login();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main();

    }

    public void login() throws IOException {

        repository = new Repository();
        DAO = new DataAccessObject();
        reportTaskHandler = new ReportTaskRepository();

        customerList = DAO.getCustomerList();
        shoeList = DAO.getShoeList();
        customerListPost = repository.getCustomerList();

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

    }
}


