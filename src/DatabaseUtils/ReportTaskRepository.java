package DatabaseUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ReportTaskRepository {

    public void printCustomerOrderCount() {

        Map<Integer, Integer> customerOrderCount = new HashMap<>();

        try (Connection connection = ConnectionHandler.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT Customer.CustomerID, `Order`.OrderID FROM Customer" +
                     " JOIN OrdersPlacedBy ON Customer.CustomerID = OrdersPlacedBy.OrdersPlacedBy_Customer_ID" +
                     " JOIN `Order` ON `Order`.OrderID = OrdersPlacedBy.OrdersPlacedBy_Order_ID")) {

            while (resultSet.next()) {
                int customerID = resultSet.getInt("CustomerID");
                if (customerOrderCount.containsKey(customerID)) {
                    customerOrderCount.put(customerID, customerOrderCount.get(customerID) + 1);
                } else {
                    customerOrderCount.put(customerID, 1);
                }
            }

            for (Map.Entry<Integer, Integer> entry : customerOrderCount.entrySet()) {
                int customerID = entry.getKey();
                int orderCount = entry.getValue();
                ResultSet customerResultSet = statement.executeQuery("SELECT FirstName, LastName FROM Customer WHERE CustomerID = " + customerID);
                customerResultSet.next();
                String firstName = customerResultSet.getString("FirstName");
                String lastName = customerResultSet.getString("LastName");
                System.out.println(firstName + " " + lastName + " har lagt " + orderCount + " ordrar.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Klar med rapporten.");
        }
    }

    public void printCustomerPaidTotal() {
        // Connect to the database
        try (Connection connection = ConnectionHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT Customer.FirstName, Customer.LastName, OrderDetails.Quantity, Shoe.Price " +
                     "FROM Customer " +
                     "JOIN OrdersPlacedBy ON Customer.CustomerID = OrdersPlacedBy.OrdersPlacedBy_Customer_ID " +
                     "JOIN `Order` ON OrdersPlacedBy.OrdersPlacedBy_Order_ID = `Order`.OrderID " +
                     "JOIN OrderDetails ON `Order`.OrderID = OrderDetails.OrderDetails_Order_ID " +
                     "JOIN Shoe ON OrderDetails.OrderDetails_Shoe_ID = Shoe.ShoeID");
             ResultSet resultSet = statement.executeQuery()) {

            // Map to store the total amount ordered by each customer
            Map<String, Integer> customerOrders = new HashMap<>();

            // Iterate through the result set and calculate the total amount ordered by each customer
            while (resultSet.next()) {
                String customerName = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
                int quantity = resultSet.getInt("Quantity");
                int price = resultSet.getInt("Price");
                int total = price * quantity;

                if (customerOrders.containsKey(customerName)) {
                    customerOrders.put(customerName, customerOrders.get(customerName) + total);
                } else {
                    customerOrders.put(customerName, total);
                }
            }

            // Print the report
            System.out.println("Kundnamn\tTotalt ordervärde");
            for (Map.Entry<String, Integer> entry : customerOrders.entrySet()) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printOrderValueByLocation() {
        try (Connection connection = ConnectionHandler.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT `Order`.OrderID, `Order`.OrderDate, Location.Name, Customer.FirstName, Customer.LastName, OrderDetails.Quantity, Shoe.Price " +
                     "FROM `Order` " +
                     "JOIN Location ON `Order`.Order_Location_ID = Location.LocationID " +
                     "JOIN OrdersPlacedBy ON OrdersPlacedBy.OrdersPlacedBy_Order_ID = `Order`.OrderID " +
                     "JOIN Customer ON OrdersPlacedBy.OrdersPlacedBy_Customer_ID = Customer.CustomerID " +
                     "JOIN OrderDetails ON OrderDetails.OrderDetails_Order_ID = `Order`.OrderID " +
                     "JOIN Shoe ON OrderDetails.OrderDetails_Shoe_ID = Shoe.ShoeID")) {

            Map<String, Double> orderValueByLocation = new HashMap<>();
            while (resultSet.next()) {
                String locationName = resultSet.getString("Name");
                double orderValue = resultSet.getInt("Quantity") * resultSet.getDouble("Price");
                if (orderValueByLocation.containsKey(locationName)) {
                    orderValueByLocation.put(locationName, orderValueByLocation.get(locationName) + orderValue);
                } else {
                    orderValueByLocation.put(locationName, orderValue);
                }
            }

            System.out.println("Ordervärde enligt plats");
            System.out.println("Ortnamn\tOrdervärde");
            for (Map.Entry<String, Double> entry : orderValueByLocation.entrySet()) {
                System.out.printf("%s\t\t%.2f\n", entry.getKey(), entry.getValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
