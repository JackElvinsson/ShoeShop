import Customer.Customer;
import Order.Order;
import Order.OrderDetails;
import Shoe.Shoe;
import Shoe.Brand;
import Shoe.Model;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DataAccessObject {

    // Skapa ett nytt Repository-objekt
    Repository repository = new Repository();

    List<Shoe> shoeList;
    List<Customer> customerList;
    List<Order> orderList;
    List<OrderDetails> orderDetailsList;
    List<Brand> brandList;
    List<Model> modelList;

    public List<Shoe> getShoeList() {
        return shoeList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public List<Model> getModelList() {
        return modelList;
    }

    public DataAccessObject() {

        // lista med skor från databasen
        try {
            shoeList = repository.getShoeList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // lista med kunder från databasen
        try {
            customerList = repository.getCustomerList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // lista med ordrar från databasen
        try {
            orderList = repository.getOrderList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // lista med orderDetails från databasen
        try {
            orderDetailsList = repository.getOrderDetailsList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // lista med varumärken från databasen
        try {
            brandList = repository.getBrandList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // lista med modeller från databasen
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

//    public List<String> getPrices(List<Shoe> shoeList) {
//
//        // Skapa en lista med priser
//        return shoeList.stream().map(Shoe::getShoePrice).distinct().collect(Collectors.toList());
//    }

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

//    public void matchShoeToBrandAndModel(List<Shoe> shoeList, List<Brand> brandList, List<Model> modelList) {
//        for (Shoe shoe : shoeList) {
//            for (Brand brand : brandList) {
//                if (shoe.getShoe_brandID() == brand.getBrandID()) {
//                    for (Model model : modelList) {
//                        if (shoe.getShoe_modelID() == model.getModelID()) {
//                            System.out.println("Shoe with ID " + shoe.getShoeID() + " has brand " + brand.getBrandName() + " and model " + model.getModelName());
//                            break;
//                        }
//                    }
//                    break;
//                }
//            }
//        }
//    }
}
