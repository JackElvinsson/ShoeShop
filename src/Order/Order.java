package Order;

import java.util.Date;

public class Order {

    private int orderID;
    private String orderDate;

    // FK
    private int order_locationID;

    public Order() {
    }

    public Order(int orderID, String orderDate, int order_locationID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.order_locationID = order_locationID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrder_locationID() {
        return order_locationID;
    }

    public void setOrder_locationID(int order_locationID) {
        this.order_locationID = order_locationID;
    }
}
