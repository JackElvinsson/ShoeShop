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
        DataAccessObject dao = new DataAccessObject();
        List<Customer> customerList = repository.getCustomerList();
        List<Shoe> shoeList = repository.getShoeList();

        UserLogin ul=new UserLogin();
        ul.login(customerList);
        ul.addToCart(shoeList);

//         Testar att hämta en lista med skor från databasen och skriva ut den i konsolen
//        List<Shoe> shoeList = repository.getShoeList();
//        System.out.println("ShoeID Inventory Color Size Price Sales Shoe_Brand_ID Shoe_Model_ID");
//        shoeList.forEach(shoe -> System.out.println(shoe.getShoeID()
//                + " " + shoe.getInventory() + " " + shoe.getShoeColor()
//                + " " + shoe.getShoeSize() + " " + shoe.getShoePrice()
//                + " " + shoe.getShoeSales() + " " + shoe.getBrand().getBrandID()
//                + " " + shoe.getModel().getModelID()));
//        System.out.println(dao.getSizes(shoeList).get(0));

//         Testar att hämta en lista med kunder från databasen och skriva ut den i konsolen
//        List<Customer> customerList = repository.getCustomerList();
//        System.out.println("CustomerID FirstName LastName Password Customer_locationID");
//        customerList.forEach(customer -> System.out.println(customer.getCustomerID()
//                + " " + customer.getFirstName() + " " + customer.getLastName()
//                + " " + customer.getPassword() + " " + customer.getCustomer_locationID()));
//
//        login(customerList);

//        // Testar att hämta en lista med ordrar från databasen och skriva ut den i konsolen
//        List<Order> orderList = repository.getOrderList();
//        System.out.println("OrderID OrderDate Order_locationID");
//        orderList.forEach(order -> System.out.println(order.getOrderID()
//                + " " + order.getOrderDate() + " " + order.getOrder_locationID()));
//
//        // Testar att hämta en lista med orderDetails från databasen och skriva ut den i konsolen
//        List<OrderDetails> orderDetailsList = repository.getOrderDetailsList();
//        System.out.println("OrderDetails_orderID orderDetails_ShoeID Quantity");
//        orderDetailsList.forEach(orderDetpails -> System.out.println(orderDetails.getOrderdetails_orderID()
//                + " " + orderDetails.getOrderdetails_shoeID() + " " + orderDetails.getQuantity()));
//
//        // Testar att hämta en lista med varumärken från databasen och skriva ut den i konsolen
//        List<Brand> brandList = repository.getBrandList();
//        System.out.println("BrandID BrandName");
//        brandList.forEach(brand -> System.out.println(brand.getBrandID()
//                + " " + brand.getBrandName()));
//
        // Testar att hämta en lista med modeller från databasen och skriva ut den i konsolen
//        List<Model> modelList = repository.getModelList();
//        System.out.println("ModelID ModelName");
//        modelList.forEach(model -> System.out.println(model.getModelID()
//                + " " + model.getModelName()));
////


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


