package Customer;

public class Customer {

    private int customerID;
    private String firstName;
    private String lastName;
    private String password;

    private boolean isLoggedIn;

    private int customer_locationID;




    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean setIsLoggedIn) {
        this.isLoggedIn = setIsLoggedIn;
    }


    // FK


    public Customer() {
    }

    public Customer(int customerID, String firstName, String lastName, String password, int customer_locationID) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.customer_locationID = customer_locationID;
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

    public int getCustomer_locationID() {
        return customer_locationID;
    }

    public void setCustomer_locationID(int customer_locationID) {
        this.customer_locationID = customer_locationID;
    }
}
