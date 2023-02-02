package Order;

public class OrderDetails {

    private int quantity;

    //FK
    private int orderdetails_shoeID;
    private int orderdetails_orderID;

    public OrderDetails() {
    }

    public OrderDetails(int quantity, int orderdetails_shoeID, int orderdetails_orderID) {
        this.quantity = quantity;
        this.orderdetails_shoeID = orderdetails_shoeID;
        this.orderdetails_orderID = orderdetails_orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderdetails_shoeID() {
        return orderdetails_shoeID;
    }

    public void setOrderdetails_shoeID(int orderdetails_shoeID) {
        this.orderdetails_shoeID = orderdetails_shoeID;
    }

    public int getOrderdetails_orderID() {
        return orderdetails_orderID;
    }

    public void setOrderdetails_orderID(int orderdetails_orderID) {
        this.orderdetails_orderID = orderdetails_orderID;
    }
}
