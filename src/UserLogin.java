import Customer.Customer;

import java.util.List;
import java.util.Scanner;

public class UserLogin {



    public static void login(List<Customer> customerList){
    Scanner sc = new Scanner(System.in);

        System.out.print("Enter first name: ");
    String firstName = sc.nextLine();
        System.out.print("Enter password: ");
    String password = sc.nextLine();

    // Använder lambda för att kontrollera om användaren finns. Ignonerar case på 'firstname' aka username.
    Customer customer = customerList.stream().filter(c -> c.getFirstName().equalsIgnoreCase(firstName) && c.getPassword().equals(password)).findFirst().orElse(null);

        if (customer != null) {
        System.out.println("Welcome, " + firstName + "!");
        customer.setIsLoggedIn(true);
        System.out.println(customer.getFirstName() + " Loggedin="+customer.isLoggedIn());
        //start addtocart-method...
    } else {
        System.out.println("Invalid input");
    }
}
}
