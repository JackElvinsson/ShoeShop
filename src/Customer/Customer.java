package Customer;

public class Customer {

    private int customerI;
    private String firstName;
    private String lastName;
    private String password;

    // FK
    private int customer_locationID;

    public Customer() {
    }

    public Customer(int customerI, String firstName, String lastName, String password, int customer_locationID) {
        this.customerI = customerI;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.customer_locationID = customer_locationID;
    }

    public int getCustomerID() {
        return customerI;
    }

    public void setCustomerID(int customerI) {
        this.customerI = customerI;
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

    public int getCustomer_locationID() {
        return customer_locationID;
    }

    public void setCustomer_locationID(int customer_locationID) {
        this.customer_locationID = customer_locationID;
    }
}
