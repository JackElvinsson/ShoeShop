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

    public List<Shoe> getShoeList() throws IOException {

        try (
                Connection connection = ConnectionHandler.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT shoe.ShoeID, shoe.Inventory, shoe.Color, shoe.Size, shoe.Price, shoe.Sales, brand.BrandID, brand.BrandName, model.ModelID, model.ModelName\n" +
                        "FROM shoe\n" +
                        "JOIN brand ON shoe.Shoe_Brand_ID = brand.BrandID\n" +
                        "JOIN model ON shoe.Shoe_Model_ID = model.ModelID;");) {

            List<Shoe> shoeList = new ArrayList<>();

            while (resultSet.next()) {

                Shoe tempShoe = new Shoe();

                int shoeID = resultSet.getInt("ShoeID");
                tempShoe.setShoeID(shoeID);
                int inventory = resultSet.getInt("Inventory");
                tempShoe.setInventory(inventory);
                String color = resultSet.getString("Color");
                tempShoe.setShoeColor(color);
                int size = resultSet.getInt("Size");
                tempShoe.setShoeSize(size);
                double price = resultSet.getDouble("Price");
                tempShoe.setShoePrice(price);
                int sales = resultSet.getInt("Sales");
                tempShoe.setShoeSales(sales);
                int brandID = resultSet.getInt("BrandID");
                String brandName = resultSet.getString("BrandName");
                tempShoe.setBrand(new Brand(brandID, brandName));
                int modelID = resultSet.getInt("ModelID");
                String modelName = resultSet.getString("ModelName");
                tempShoe.setModel(new Model(modelID, modelName));

                shoeList.add(tempShoe);

            }
            return shoeList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Brand> getBrandList() throws IOException {

        try (
                Connection connection = ConnectionHandler.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT BrandID, BrandName FROM brand");) {

            List<Brand> brandList = new ArrayList<>();

            while (resultSet.next()) {

                Brand tempBrand = new Brand();

                int brandID = resultSet.getInt("BrandID");
                tempBrand.setBrandID(brandID);
                String brandName = resultSet.getString("BrandName");
                tempBrand.setBrandName(brandName);

                brandList.add(tempBrand);

            }
            return brandList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Model> getModelList() throws IOException {

        try (
                Connection connection = ConnectionHandler.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT ModelID, ModelName FROM model");) {

            List<Model> modelList = new ArrayList<>();

            while (resultSet.next()) {

                Model tempModel = new Model();

                int modelID = resultSet.getInt("ModelID");
                tempModel.setModelID(modelID);
                String modelName = resultSet.getString("ModelName");
                tempModel.setModelName(modelName);

                modelList.add(tempModel);

            }
            return modelList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getCustomerList() throws IOException {

        try (
                Connection connection = ConnectionHandler.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT Customer.CustomerID, Customer.FirstName, Customer.LastName, Customer.Passwordd, Location.LocationID, Location.Name FROM Customer\n" +
                        "JOIN Location ON Customer.Customer_Location_ID = Location.LocationID");) {

            List<Customer> customerList = new ArrayList<>();

            while (resultSet.next()) {

                Customer tempCustomer = new Customer();

                int customerID = resultSet.getInt("CustomerID");
                tempCustomer.setCustomerID(customerID);
                String firstName = resultSet.getString("FirstName");
                tempCustomer.setFirstName(firstName);
                String lastName = resultSet.getString("LastName");
                tempCustomer.setLastName(lastName);
                String password = resultSet.getString("Passwordd");
                tempCustomer.setPassword(password);
                int locationID = resultSet.getInt("LocationID");
                String locationName = resultSet.getString("Name");
                tempCustomer.setLocation(new Location(locationID, locationName));
                tempCustomer.setOrderList(getOrdersForCustomer(customerID)); //hämtar orderlista för kunden.
                customerList.add(tempCustomer);

            }
            return customerList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public List<Order> getOrdersForCustomer(int customerId) throws IOException {
        try (
                Connection connection = ConnectionHandler.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT Order.OrderID, Order.Date,OrderDetail.OrderDetails_Order_ID, OrderDetails.OrderDetails_Shoe_ID, OrderDetails.Quantity FROM Order\n" +
                        "JOIN OrderDetails ON Order.OrderID = OrderDetail.OrderDetails_Order_ID\n" +
                        "WHERE Order.Customer_CustomerID = ?");
        ) {
            statement.setInt(1, customerId);

            ResultSet resultSet = statement.executeQuery();

            List<Order> orderList = new ArrayList<>();
            Map<Integer, Order> orderMap = new HashMap<>();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("OrderID");
                Order tempOrder = orderMap.get(orderId);
                if (tempOrder == null) {
                    tempOrder = new Order();
                    tempOrder.setOrderID(orderId);
                    LocalDate date = resultSet.getDate("Date").toLocalDate();
                    tempOrder.setOrderDate(date);
                    orderMap.put(orderId, tempOrder);
                    orderList.add(tempOrder);
                }
                int orderDetails_Shoe_ID = resultSet.getInt("OrderDetails_Shoe_ID");
                int quantity = resultSet.getInt("Quantity");
                tempOrder.getOrderDetailsList().add(new OrderDetails(quantity, orderDetails_Shoe_ID, orderId));
            }

            return orderList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public List<Order> getOrderList() throws IOException {

        try (
                Connection connection =ConnectionHandler.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT OrderID, OrderDate, Order_Location_ID FROM `Order`");) {

            List<Order> orderList = new ArrayList<>();

            while (resultSet.next()) {

                Order tempOrder = new Order();

                int orderID = resultSet.getInt("OrderID");
                tempOrder.setOrderID(orderID);
                LocalDate orderDate = resultSet.getDate("OrderDate").toLocalDate();
                tempOrder.setOrderDate(orderDate);
                int order_locationID = resultSet.getInt("Order_Location_ID");
                tempOrder.setOrder_locationID(order_locationID);

                orderList.add(tempOrder);

            }
            return orderList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrderDetails> getOrderDetailsList() throws IOException {

        try (
                Connection connection = ConnectionHandler.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT Quantity, Orderdetails_Shoe_ID, Orderdetails_Order_ID FROM OrderDetails");) {

            List<OrderDetails> orderDetailsList = new ArrayList<>();

            while (resultSet.next()) {

                OrderDetails tempOrder = new OrderDetails();

                int quantity = resultSet.getInt("Quantity");
                tempOrder.setQuantity(quantity);
                int orderdetails_shoeID = resultSet.getInt("Orderdetails_Shoe_ID");
                tempOrder.setOrderdetails_shoeID(orderdetails_shoeID);
                int orderdetails_orderID = resultSet.getInt("Orderdetails_Order_ID");
                tempOrder.setOrderdetails_orderID(orderdetails_orderID);

                orderDetailsList.add(tempOrder);

            }
            return orderDetailsList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToCart(int shoeID, List<Customer> customerList) throws IOException {

        try (
                Connection connection = ConnectionHandler.getConnection(); ){



            String callStoredProcedure = "{ call AddToCart(?,?,?) }";
            CallableStatement cs = connection.prepareCall(callStoredProcedure);
            cs.setInt(1, getCurrentCustomerID(customerList));
            cs.setInt(2, getOrderList().size() + 1);
            cs.setInt(3, shoeID);
            cs.execute();

            System.out.println("Successfully added to cart");

        } catch (SQLException e) {

            System.out.println("Failed to add to cart");
            throw new RuntimeException(e);
        }
    }

    //TODO: Implementera denna metod
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


