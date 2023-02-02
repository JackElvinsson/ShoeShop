import Customer.Customer;
import Order.Order;
import Order.OrderDetails;
import Shoe.Shoe;
import Shoe.Brand;
import Shoe.Model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> getBrands(List<Brand> brandList) {

        // Skapa en lista med varumärken
        return brandList.stream().map(Brand::getBrandName).distinct().collect(Collectors.toList());
    }

    public List<String> getModels(List<Model> modelList) {

        // Skapa en lista med modeller
        return modelList.stream().map(Model::getModelName).distinct().collect(Collectors.toList());
    }

    public List<String> getColors(List<Shoe> shoeList) {

        // Skapa en lista med färger
        return shoeList.stream().map(Shoe::getShoeColor).distinct().collect(Collectors.toList());
    }

    public List<Integer> getSizes(List<Shoe> shoeList) {

        // Skapa en lista med storlekar
        return shoeList.stream().map(Shoe::getShoeSize).distinct().collect(Collectors.toList());
    }

    public List<String> getPrices(List<Shoe> shoeList) {

        // Skapa en lista med priser
        return shoeList.stream().map(Shoe::getShoePrice).distinct().collect(Collectors.toList());
    }

    public List<Integer> getSales(List<Shoe> shoeList) {

        // Skapa en lista med försäljning
        return shoeList.stream().map(Shoe::getShoeSales).collect(Collectors.toList());
    }

    public List<Integer> getInventories(List<Shoe> shoeList) {

        // Skapa en lista med lager
        return shoeList.stream().map(Shoe::getInventory).collect(Collectors.toList());
    }

    public List<Integer> getShoeIDs(List<Shoe> shoeList) {

        // Skapa en lista med skoID
        return shoeList.stream().map(Shoe::getShoeID).distinct().collect(Collectors.toList());
    }

    public List<Integer> getCustomerIDs(List<Customer> customerList) {

        // Skapa en lista med kundID
        return customerList.stream().map(Customer::getCustomerID).distinct().collect(Collectors.toList());
    }

    public List<String> getCustomerNames(List<Customer> customerList) {

        // Skapa en lista med kundnamn
        return customerList.stream().map(c -> c.getFirstName() + " " + c.getLastName()).collect(Collectors.toList());
    }

}
