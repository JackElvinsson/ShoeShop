package DatabaseUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ReportTaskRepository {

    public void printCustomerOrderCount() {

        // En map som lagrar kundens ID och antal ordrar
        Map<Integer, Integer> customerOrderCount = new HashMap<>();

        // Koppla upp mot databasen

        try (Connection connection = ConnectionHandler.getConnection();
             PreparedStatement countStatement = connection.prepareStatement("SELECT Customer.CustomerID, `Order`.OrderID FROM Customer" +
                     " JOIN OrdersPlacedBy ON Customer.CustomerID = OrdersPlacedBy.OrdersPlacedBy_Customer_ID" +
                     " JOIN `Order` ON `Order`.OrderID = OrdersPlacedBy.OrdersPlacedBy_Order_ID");
             ResultSet resultSet = countStatement.executeQuery()) {

            // Iterera över alla rader i resultsetet och lägg till kundens orderantal i mapen customerOrderCount om kunden redan finns där, annars skapa en ny post
            // customerOrderCount.containsKey(customerID) returnerar true om customerOrderCount innehåller en post med nyckeln customerID

            while (resultSet.next()) {
                int customerID = resultSet.getInt("CustomerID");
                if (customerOrderCount.containsKey(customerID)) {
                    customerOrderCount.put(customerID, customerOrderCount.get(customerID) + 1);
                } else {
                    customerOrderCount.put(customerID, 1);
                }
            }

            // Itererar över alla poster i mapen customerOrderCount
            // Eftersom vi hittils bara har kundens ID så hämtas för varje post i mapen kundens namn från databasen och skrivs ut tillsammans med kundens orderantal
            // För att hämta kundens namn från databasen används alltså ett nytt statement och ett nytt resultset

            // customerOrderCount.entrySet() returnerar en collection av alla poster i mapen
            for (Map.Entry<Integer, Integer> entry : customerOrderCount.entrySet()) {

                // för varje post i mapen hämtas kundens ID och orderantal
                int customerID = entry.getKey();
                int orderCount = entry.getValue();

                // Skapa ett nytt statement och resultset för att hämta kundens namn
                // PreparedStatement används igen för att undvika SQL-injections
                // PreparedStatement är en subklass till Statement och har en metod setInt() som gör att vi kan ange vilket värde som ska ersätta frågetecknet i SQL-satsen
                PreparedStatement customerStatement = connection.prepareStatement("SELECT FirstName, LastName FROM Customer WHERE CustomerID = ?");
                customerStatement.setInt(1, customerID);
                // Eftersom vi inte har några parametrar i SQL-satsen kan vi använda executeQuery() direkt
                ResultSet customerResultSet = customerStatement.executeQuery();

                // Eftersom vi vet att det bara kommer finnas en rad i resultsetet kan vi använda next() för att gå till den första raden
                customerResultSet.next();
                String firstName = customerResultSet.getString("FirstName");
                String lastName = customerResultSet.getString("LastName");
                System.out.println(firstName + " " + lastName + " har lagt " + orderCount + " ordrar.");
            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void printCustomerPaidTotal() {

        // Koppla upp mot databasen

        try (Connection connection = ConnectionHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT Customer.FirstName, Customer.LastName, OrderDetails.Quantity, Shoe.Price " +
                     "FROM Customer " +
                     "JOIN OrdersPlacedBy ON Customer.CustomerID = OrdersPlacedBy.OrdersPlacedBy_Customer_ID " +
                     "JOIN `Order` ON OrdersPlacedBy.OrdersPlacedBy_Order_ID = `Order`.OrderID " +
                     "JOIN OrderDetails ON `Order`.OrderID = OrderDetails.OrderDetails_Order_ID " +
                     "JOIN Shoe ON OrderDetails.OrderDetails_Shoe_ID = Shoe.ShoeID");
             ResultSet resultSet = statement.executeQuery()) {

            // En map som lagrar kundens namn och totala ordervärde
            Map<String, Integer> customerOrders = new HashMap<>();

            // Iterera över alla rader i resultsetet och lägg till kundens ordervärde i mapen customerOrders om kunden redan finns där, annars skapa en ny post
            while (resultSet.next()) {
                String customerName = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
                int quantity = resultSet.getInt("Quantity");
                int price = resultSet.getInt("Price");
                int total = price * quantity;

                // Om kunden redan finns i mapen lägg till ordervärdet till det totala ordervärdet, annars skapa en ny post
                if (customerOrders.containsKey(customerName)) {
                    customerOrders.put(customerName, customerOrders.get(customerName) + total);
                } else {
                    customerOrders.put(customerName, total);
                }
            }

            // Skriv ut rapporten
            System.out.println("Kundnamn\tTotalt ordervärde");
            for (Map.Entry<String, Integer> entry : customerOrders.entrySet()) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printOrderValueByLocation() {

        // Koppla upp mot databasen
        try (Connection connection = ConnectionHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT `Order`.OrderID, `Order`.OrderDate, Location.Name, Customer.FirstName, Customer.LastName, OrderDetails.Quantity, Shoe.Price " +
                     "FROM `Order` " +
                     "JOIN Location ON `Order`.Order_Location_ID = Location.LocationID " +
                     "JOIN OrdersPlacedBy ON OrdersPlacedBy.OrdersPlacedBy_Order_ID = `Order`.OrderID " +
                     "JOIN Customer ON OrdersPlacedBy.OrdersPlacedBy_Customer_ID = Customer.CustomerID " +
                     "JOIN OrderDetails ON OrderDetails.OrderDetails_Order_ID = `Order`.OrderID " +
                     "JOIN Shoe ON OrderDetails.OrderDetails_Shoe_ID = Shoe.ShoeID");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // En map som lagrar ordervärdet för varje ort
            Map<String, Double> orderValueByLocation = new HashMap<>();

            // Itererar över alla rader i resultsetet och lägger till ordervärdet för orten i mapen orderValueByLocation om orten redan finns där, annars skapas en ny post
            // Ordervärdet beräknas genom att multiplicera antalet skor med priset för skon
            // Detta görs för varje rad i resultsetet
            // Detta gör att om en kund har lagt flera ordrar på samma ort så kommer ordervärdet för den orten att öka med ordervärdet för varje order
            while (resultSet.next()) {
                String locationName = resultSet.getString("Name");
                double orderValue = resultSet.getInt("Quantity") * resultSet.getDouble("Price");

                // Om orten redan finns i mapen lägg till ordervärdet till det totala ordervärdet, annars skapa en ny post
                if (orderValueByLocation.containsKey(locationName)) {
                    orderValueByLocation.put(locationName, orderValueByLocation.get(locationName) + orderValue);
                } else {
                    orderValueByLocation.put(locationName, orderValue);
                }
            }

            // Skriv ut rapporten
            System.out.println("Ordervärde enligt plats");
            System.out.println("Ortnamn\tOrdervärde");
            for (Map.Entry<String, Double> entry : orderValueByLocation.entrySet()) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
