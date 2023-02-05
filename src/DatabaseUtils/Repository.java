package DatabaseUtils;

import Customer.*;
import Order.Order;
import Shoe.Shoe;
import Order.OrderDetails;
import Shoe.Brand;
import Shoe.Model;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


public class Repository {

    // Dessa metoder hämtar en lista med objekt från databasen
    // De använder en funktion som tar emot en query-sträng samt ett resultSet<T> och som sedan returnerar ett objekt som läggs till i en lista
    // De returnerar till sist en lista med objekt
    public List<Shoe> getShoeList() throws IOException {

        String query = "SELECT shoe.ShoeID, shoe.Inventory, shoe.Color, shoe.Size, shoe.Price, shoe.Sales, brand.BrandID, brand.BrandName, model.ModelID, model.ModelName " +
                "FROM shoe " +
                "JOIN brand ON shoe.Shoe_Brand_ID = brand.BrandID " +
                "JOIN model ON shoe.Shoe_Model_ID = model.ModelID;";

        // Använder en lambda-funktion för att skapa ett Shoe-objekt
        // Lambda-funktionen tar emot ett resultSet och returnerar ett Shoe-objekt med värden från resultSet
        // getList returnerar sedan en lista med Shoe-objekt
        return getList(query, resultSet -> {

            Shoe tempShoe = new Shoe();

            try {

                tempShoe.setShoeID(resultSet.getInt("ShoeID"));
                tempShoe.setInventory(resultSet.getInt("Inventory"));
                tempShoe.setShoeColor(resultSet.getString("Color"));
                tempShoe.setShoeSize(resultSet.getInt("Size"));
                tempShoe.setShoePrice(resultSet.getDouble("Price"));
                tempShoe.setShoeSales(resultSet.getInt("Sales"));
                tempShoe.setBrand(new Brand(resultSet.getInt("BrandID"), resultSet.getString("BrandName")));
                tempShoe.setModel(new Model(resultSet.getInt("ModelID"), resultSet.getString("ModelName")));

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return tempShoe;
        });
    }

    public List<Brand> getBrandList() throws IOException {

        String query = "SELECT BrandID, BrandName FROM brand";

        return getList(query, resultSet -> {

            Brand tempBrand = new Brand();

            try {

                tempBrand.setBrandID(resultSet.getInt("BrandID"));
                tempBrand.setBrandName(resultSet.getString("BrandName"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return tempBrand;
        });
    }

    public List<Model> getModelList() throws IOException {

        String query = "SELECT model.ModelID, model.ModelName FROM model";

        return getList(query, resultSet -> {

            Model tempModel = new Model();

            try {

                tempModel.setModelID(resultSet.getInt("ModelID"));
                tempModel.setModelName(resultSet.getString("ModelName"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return tempModel;
        });
    }

    public List<Customer> getCustomerList() throws IOException {

        String query = "SELECT Customer.CustomerID, Customer.FirstName, Customer.LastName, Customer.Passwordd, Location.LocationID, Location.Name FROM Customer\n" +
                "JOIN Location ON Customer.Customer_Location_ID = Location.LocationID";

        return getList(query, resultSet -> {

            Customer tempCustomer = new Customer();

            try {

                tempCustomer.setCustomerID(resultSet.getInt("CustomerID"));
                tempCustomer.setFirstName(resultSet.getString("FirstName"));
                tempCustomer.setLastName(resultSet.getString("LastName"));
                tempCustomer.setPassword(resultSet.getString("Passwordd"));
                tempCustomer.setLocation(new Location(resultSet.getInt("LocationID"), resultSet.getString("Name")));
                tempCustomer.setOrderList(getOrdersForCustomer(resultSet.getInt("CustomerID")));

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
            return tempCustomer;
        });
    }

    public List<Order> getOrdersForCustomer(int customerId) throws IOException {
        String query = "SELECT Order.OrderID, Order.OrderDate, Order.Order_Location_ID,Location.Name, OrderDetails.OrderDetails_Order_ID, OrderDetails.OrderDetails_Shoe_ID, OrderDetails.Quantity " +
                "FROM `Order` " +
                "JOIN OrderDetails ON Order.OrderID = OrderDetails.OrderDetails_Order_ID " +
                "JOIN OrdersPlacedBy ON Order.OrderID = OrdersPlacedBy.OrdersPlacedBy_Order_ID " +
                "JOIN Location ON Order.Order_Location_ID = LocationID " +
                "WHERE OrdersPlacedBy.OrdersPlacedBy_Customer_ID = " + customerId;

        return getList(query, resultSet -> {

            Order tempOrder = new Order();

            try {

                tempOrder.setOrderID(resultSet.getInt("OrderID"));
                tempOrder.setOrderDate(resultSet.getDate("OrderDate").toLocalDate());
                tempOrder.setOrder_locationID(new Location(resultSet.getInt("Order_Location_ID"), resultSet.getString("Name")));
                tempOrder.setOrderDetailsList(new ArrayList<>());
                tempOrder.getOrderDetailsList().add(new OrderDetails(resultSet.getInt("Quantity"), resultSet.getInt("Order_Location_ID"), resultSet.getInt("OrderID")));

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return tempOrder;
        });
    }

    public List<Order> getOrderList() {

        String query = "SELECT OrderID, OrderDate, Order_Location_ID, LocationID, Name FROM `Order` JOIN Location ON Order.Order_Location_ID = Location.LocationID";

        return getList(query, resultSet -> {

                    Order tempOrder = new Order();

                    try {

                        tempOrder.setOrderID(resultSet.getInt("OrderID"));
                        tempOrder.setOrderDate(resultSet.getDate("OrderDate").toLocalDate());
                        tempOrder.setOrder_locationID(new Location(resultSet.getInt("LocationID"), resultSet.getString("Name")));

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return tempOrder;
                }
        );
    }

    public List<OrderDetails> getOrderDetailsList() throws IOException {

        String query = "SELECT Quantity, Orderdetails_Shoe_ID, Orderdetails_Order_ID FROM OrderDetails";

        return getList(query, resultSet -> {

            OrderDetails tempOrder = new OrderDetails();

            try {

                tempOrder.setQuantity(resultSet.getInt("Quantity"));
                tempOrder.setOrderdetails_shoeID(resultSet.getInt("Orderdetails_Shoe_ID"));
                tempOrder.setOrderdetails_orderID(resultSet.getInt("Orderdetails_Order_ID"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return tempOrder;
        });
    }

    // Denna metod kallar på en stored procedure som lägger till en sko i kundens kundvagn
    // Den tar emot ett skoID och en lista med kunder, letar upp den inloggade kundens ID och lägger till skon i kundens kundvagn
    public void addToCart(int shoeID, List<Customer> customerList) throws IOException {

        try (Connection connection = ConnectionHandler.getConnection();) {


            String callStoredProcedure = "{ call AddToCart(?,?,?) }";
            CallableStatement cs = connection.prepareCall(callStoredProcedure);
            cs.setInt(1, getCurrentCustomerID(customerList));
            cs.setInt(2, getOrderList().size() + 1);
            cs.setInt(3, shoeID);
            cs.execute();

            System.out.println("Skon har lagts till i kundvagnen");

        } catch (SQLException e) {

            System.out.println("Lyckades inte lägga till skon i kundvagnen");
            throw new RuntimeException(e);
        }
    }


    // Denna metod är en generisk metod som tar emot en query-sträng och en funktion som tar emot en ResultSet och returnerar en lista av objekt
    // Den används för att hämta data från databasen och returnera en lista av objekt
    private <T> List<T> getList(String query, Function<ResultSet, T> rowMapper) {
        try (
                Connection connection = ConnectionHandler.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            List<T> list = new ArrayList<>();
            while (resultSet.next()) {
                T item = rowMapper.apply(resultSet);
                list.add(item);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCurrentCustomerID(List<Customer> customerList) {

        return customerList.stream()
                .filter(c -> c.isLoggedIn())
                .findFirst()
                .map(Customer::getCustomerID)
                .orElse(0);
    }
}


