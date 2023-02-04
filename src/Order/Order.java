package Order;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private int orderID;
    private LocalDate orderDate; //skulle kunna köras som Date istället för string.

    private List<OrderDetails> orderDetailsList;

    // FK
    private int order_locationID; //vet ej om vi ska spara denna. Ska odern kunna ha en annan plats än kunden? Kan också göra som till en location om det finns behov. Finns inget behov att ändra än så länge, låter vara.



    public Order() {
    }

    public Order(int orderID, LocalDate orderDate, int order_locationID) {
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrder_locationID() {
        return order_locationID;
    }

    public void setOrder_locationID(int order_locationID) {
        this.order_locationID = order_locationID;
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetails) {
        this.orderDetailsList = orderDetails;
    }
}
