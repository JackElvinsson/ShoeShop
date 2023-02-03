import Customer.Customer;
import Shoe.Brand;
import Shoe.Model;
import Shoe.Shoe;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        /**
         * Skapa klasser som representerar varje tabell i databasen.
         Dessa klasser ska inkludera fält som motsvarar kolumnerna i varje tabell och metoder för att interagera med databasen.
         Till exempel kan du skapa en Customer.Location-klass med fält för LocationID, Name, etc.

         * Skapa en klass för att hantera databasanslutningen, som en DatabaseConnection-klass.
         Denna klass ska ha metoder för att öppna en anslutning till databasen,
         utföra SQL-satser och stänga anslutningen.

         * Skapa en klass för att interagera med databasen och utföra CRUD-operationer på tabellerna.
         Till exempel kan du skapa en ShoeShop-klass med metoder för att lägga till en ny plats,
         hämta en lista över kunder, etc.
         Denna klass ska använda DatabaseConnection-klassen för att interagera med databasen.
         */


        Repository repository = new Repository();
        DataAccessObject DAO = new DataAccessObject();
        List<Customer> customerList = DAO.getCustomerList();
        List<Shoe> shoeList = DAO.getShoeList();

        int shoeId = 0;
        List<Customer> customerListPost = repository.getCustomerList();

        UserLogin ul=new UserLogin();
        customerListPost = ul.login(customerList);
        shoeId = ul.addToCart(shoeList);
        repository.addToCart(shoeId, customerListPost);






    }


//    public static void login(List<Customer> customerList){
//
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Enter first name: ");
//        String firstName = sc.nextLine();
//        System.out.print("Enter password: ");
//        String password = sc.nextLine();
//
//        // Använder lambda för att kontrollera om användaren finns. Ignonerar case på 'firstname' aka username.
//        Customer customer = customerList.stream().filter(c -> c.getFirstName().equalsIgnoreCase(firstName) && c.getPassword().equals(password)).findFirst().orElse(null);
//
//        if (customer != null) {
//            System.out.println("Welcome, " + firstName + "!");
//            customer.setIsLoggedIn(true);
//            System.out.println(customer.getFirstName() + " Loggedin="+customer.isLoggedIn());
//            //start addtocart-method...
//        } else {
//            System.out.println("Invalid input");
//        }
//}

}


