import Customer.Customer;
import Order.Order;
import Order.OrderDetails;
import Shoe.Shoe;
import Shoe.Brand;
import Shoe.Model;


import java.io.IOException;
import java.util.List;

public class DataAccessObject {

    public DataAccessObject() {

        // Skapa ett nytt Repository-objekt
        Repository repository = new Repository();

        // lista med skor från databasen
        List<Shoe> shoeList = null;
        try {
            shoeList = repository.getShoeList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // lista med kunder från databasen
        List<Customer> customerList = null;
        try {
            customerList = repository.getCustomerList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // lista med ordrar från databasen
        List<Order> orderList = null;
        try {
            orderList = repository.getOrderList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // lista med orderDetails från databasen
        List<OrderDetails> orderDetailsList = null;
        try {
            orderDetailsList = repository.getOrderDetailsList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // lista med varumärken från databasen
        List<Brand> brandList = null;
        try {
            brandList = repository.getBrandList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // lista med modeller från databasen
        List<Model> modelList = null;
        try {
            modelList = repository.getModelList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
