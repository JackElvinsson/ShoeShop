package Order;

public class OrdersPlacedBy {

    private int ordersplacedby_orderID;
    private int ordersplacedby_customerID;

    public OrdersPlacedBy(int ordersplacedby_orderID, int ordersplacedby_customerID) {
        this.ordersplacedby_orderID = ordersplacedby_orderID;
        this.ordersplacedby_customerID = ordersplacedby_customerID;
    }

    public int getOrdersplacedby_orderID() {
        return ordersplacedby_orderID;
    }

    public void setOrdersplacedby_orderID(int ordersplacedby_orderID) {
        this.ordersplacedby_orderID = ordersplacedby_orderID;
    }

    public int getOrdersplacedby_customerID() {
        return ordersplacedby_customerID;
    }

    public void setOrdersplacedby_customerID(int ordersplacedby_customerID) {
        this.ordersplacedby_customerID = ordersplacedby_customerID;
    }
}
