import Customer.Customer;
import Shoe.Shoe;

import javax.xml.crypto.Data;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserLogin {

    Scanner scanner = new Scanner(System.in);    //skannern kan användas i hela UserLogin.
    DataAccessObject DAO = new DataAccessObject();


    public void login(List<Customer> customerList) {


        Customer customer = null;

        while (customer == null) {
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Använder lambda för att se om det finns en användare som matchar angivet namn&pw. Ignores case on 'firstname' aka username.
            customer = customerList.stream().filter(c -> c.getFirstName().equalsIgnoreCase(firstName) && c.getPassword().equals(password)).findFirst().orElse(null);

            if (customer != null) {
                System.out.println("Välkommen " + firstName + "!");
                customer.setIsLoggedIn(true); //flaggar kunden som inloggad. Då endast en kan logga in behöver vi inte ha koll på något ID just nu.
                System.out.println(customer.getFirstName() + " Loggedin=" + customer.isLoggedIn());
                //start addtocart-method...
            } else {
                System.out.println("Invalid input");
            }
        }
    }


    public void addToCart(List<Shoe> shoeList) {


        System.out.println();
        System.out.println("Skriv in modellnamnet på skon du vill köpa");
        System.out.println();

        Set<String> Modeltypes = new HashSet<>();
        for (Shoe shoe : shoeList) {
            Modeltypes.add(shoe.getModel().getModelName());
        }

        for (String modelName : Modeltypes) {
            String brandName = shoeList.stream()
                    .filter(shoe -> shoe.getModel().getModelName().equalsIgnoreCase(modelName))
                    .findFirst()
                    .get()
                    .getBrand()
                    .getBrandName();
            System.out.println(String.format("%-15s %-15s", modelName, brandName));
        }


        String userInputOfShoeModel;
        Shoe matchingShoe = null;

        while (matchingShoe == null) {
            userInputOfShoeModel = scanner.nextLine();

            for (Shoe shoe : shoeList) {
                if (shoe.getModel().getModelName().equalsIgnoreCase(userInputOfShoeModel)) {
                    matchingShoe = shoe;
                    break;
                }}

            if (matchingShoe == null) {
                System.out.println("Hittade ingen skomodell med namn " + userInputOfShoeModel);
            }
        }

        System.out.println("vald sko " + matchingShoe.getBrand().getBrandName()+" "+matchingShoe.getModel().getModelName());

        System.out.println("skon finns i storlekarna: ");

            for (int i = 0; i < DAO.getSizes(shoeList).size(); i++) {
                System.out.print(DAO.getSizes(shoeList).get(i) + " - " + DAO.getColors(shoeList).get(i) + "\n");

        }
        System.out.println("Välj vilken storlek du vill köpa: ");
        int size = scanner.nextInt();

    }

    }

