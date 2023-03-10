package Login;

import Customer.Customer;
import Shoe.Shoe;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
public class UserLogin {

    Scanner scanner = new Scanner(System.in);    //skannern kan användas i hela Login.UserLogin.

    public List<Customer> login(List<Customer> customerList) {

        Customer customer = null;

        while (customer == null) {
            System.out.print("Skriv in förnamn: ");
            String firstName = scanner.nextLine();
            System.out.print("Skriv in lösenord: ");
            String password = scanner.nextLine();

            // Använder lambda för att se om det finns en användare som matchar angivet namn&pw.
            customer = customerList.stream().filter(c -> c.getFirstName().equalsIgnoreCase(firstName) && c.getPassword().equals(password)).findFirst().orElse(null);

            if (customer != null) {
                System.out.println("Välkommen " + firstName + "!");
                customer.setIsLoggedIn(true); //flaggar kunden som inloggad. Då endast en kan logga in behöver vi inte ha koll på något ID just nu.
                System.out.println(customer.getFirstName() + " Loggedin = " + customer.isLoggedIn());
            } else {
                System.out.println("Invalid input");
            }
        }
        return customerList;
    }


    public int addToCart(List<Shoe> shoeList) {

        System.out.println();
        System.out.println("Skriv in modellnamnet på skon du vill köpa");
        System.out.println();

        Set<String> Modeltypes = new HashSet<>();

        //Spara modellnamn
        for (Shoe shoe : shoeList) {
            Modeltypes.add(shoe.getModel().getModelName());
        }


        //Listar ut modellnamnen & märkesnamn
        for (String modelName : Modeltypes) {
            String brandName = shoeList.stream().filter(shoe -> shoe.getModel().getModelName().equalsIgnoreCase(modelName)).findFirst().get().getBrand().getBrandName();
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
                }
            }

            if (matchingShoe == null) {
                System.out.println("Hittade ingen skomodell med namn " + userInputOfShoeModel);
            }
        }
        //Ser till att matchingShoe blir sparad i en final.

        final Shoe finalMatchingShoe=matchingShoe;
        // Kollar så modellnamnet är likedant som på finalMatchingShoe, och att inventory är större än 0. Listar därefter tillgängliga storlekarna.
        Set<Integer> availableSizes = shoeList.stream().filter(shoe -> shoe.getModel().getModelName().equalsIgnoreCase(finalMatchingShoe.getModel().getModelName())).filter(shoe -> shoe.getInventory() > 0).map(shoe -> shoe.getShoeSize()).collect(Collectors.toSet());

        System.out.println("Tillgängliga storlekar:");
        for (Integer size : availableSizes) {
            System.out.println(size);
        }

        int UserInputOfShoeSize;
        while (true) {
            System.out.println("Vilken skostorleken du vill köpa?");
            UserInputOfShoeSize = scanner.nextInt();
            if (availableSizes.contains(UserInputOfShoeSize)) {
                break;
            }
            System.out.println("Ogiltig storlek.");
        }
        final int finalShoeSize=UserInputOfShoeSize;



        System.out.println();
        System.out.println("Tillgängliga färger:");

        //Kollar så modellnamnet är likedant som på finalMatchingShoe, at inventory är större än 0. Sen att skostorleken är lika med finalShoeSize. Visar därefter tillgängliga färger(vi har bara 1 färg per storlek dock, men ifall vi hade haft fler)
        Set<String> availableColors = shoeList.stream().filter(shoe -> shoe.getModel().getModelName().equalsIgnoreCase(finalMatchingShoe.getModel().getModelName())).filter(shoe -> shoe.getInventory() > 0).filter(shoe -> shoe.getShoeSize() == finalShoeSize).map(shoe -> shoe.getShoeColor().toLowerCase()).collect(Collectors.toSet());

        for (String color : availableColors) {
            System.out.println(color);
        }



        scanner.nextLine();
        String userInputOfColor;
        String matchingColor = null;

        System.out.println();
        while (true) {
            System.out.println("Välj färg");
            userInputOfColor = scanner.nextLine().toLowerCase();

            if (availableColors.contains(userInputOfColor)) {
                matchingColor = userInputOfColor;
                break;
            } else {
                System.out.println();
                System.out.println("Ogiltig färg");
            }
        }

        final String finalShoeColor=userInputOfColor;

        //Tar fram och retunerar shoeID för skon man valt via, finalMatchingShoe, finalShoeSize, finalShoeColor.
        return shoeList.stream().filter(shoe -> shoe.getModel().getModelName().equalsIgnoreCase(finalMatchingShoe.getModel().getModelName())).filter(shoe -> shoe.getShoeSize() == finalShoeSize).filter(shoe -> shoe.getShoeColor().equalsIgnoreCase(finalShoeColor)).findFirst().get().getShoeID();
    }

    }

