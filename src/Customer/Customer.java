package Customer;

import Order.Order;
import Order.OrderDetails;

import java.util.List;

public class Customer {

    private int customerID;
    private String firstName;
    private String lastName;
    private String password;

    private boolean isLoggedIn;

    private Location location;
    private List<Order> orderList;






    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean setIsLoggedIn) {
        this.isLoggedIn = setIsLoggedIn;
    }


    // FK


    public Customer() {
    }

    public Customer(int customerID, String firstName, String lastName, String password, Location location) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.location = location;
        isLoggedIn =false;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerI) {
        this.customerID = customerI;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
