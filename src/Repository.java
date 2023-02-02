import Customer.Customer;
import Order.Order;
import Shoe.Shoe;
import Order.OrderDetails;
import Shoe.Brand;
import Shoe.Model;

import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Repository {

    public List<Shoe> getShoeList() throws IOException {

        try (
                Connection connection = ConnectionHandler.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT ShoeID, Inventory, Color, `Size`, Price, Sales, Shoe_Brand_ID, Shoe_Model_ID FROM shoe");) {

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
                String price = resultSet.getString("Price");
                tempShoe.setShoePrice(price);
                int sales = resultSet.getInt("Sales");
                tempShoe.setShoeSales(sales);
                int shoe_BrandID = resultSet.getInt("Shoe_Brand_ID");
                tempShoe.setShoe_brandID(shoe_BrandID);
                int shoe_ModelID = resultSet.getInt("Shoe_Model_ID");
                tempShoe.setShoe_modelID(shoe_ModelID);

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
                ResultSet resultSet = statement.executeQuery("SELECT CustomerID, FirstName, LastName, Passwordd, Customer_Location_ID FROM Customer");) {

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
                int customer_locationID = resultSet.getInt("Customer_Location_ID");
                tempCustomer.setCustomer_locationID(customer_locationID);

                customerList.add(tempCustomer);

            }
            return customerList;

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
                String orderDate = resultSet.getString("OrderDate");
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
}


