package Shoe;

public class Shoe {

    private int shoeID;
    private int inventory;
    private String shoeColor;
    private int shoeSize;
    private String shoePrice;
    private int shoeSales;
    private Brand brand;
    private Model model;

     //FK
//    private int shoe_brandID;
//    private int shoe_modelID;

    //empty constructor
    public Shoe() {
    }

    public Shoe(int shoeID, int inventory, String shoeColor, int shoeSize, String shoePrice, int shoeSales) {
        this.shoeID = shoeID;
        this.inventory = inventory;
        this.shoeColor = shoeColor;
        this.shoeSize = shoeSize;
        this.shoePrice = shoePrice;
        this.shoeSales = shoeSales;
//        this.shoe_brandID = shoe_brandID;
//        this.shoe_modelID = shoe_modelID;
    }


    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getShoeID() {
        return shoeID;
    }

    public void setShoeID(int shoeID) {
        this.shoeID = shoeID;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getShoeColor() {
        return shoeColor;
    }

    public void setShoeColor(String shoeColor) {
        this.shoeColor = shoeColor;
    }

    public int getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(int shoeSize) {
        this.shoeSize = shoeSize;
    }

    public String getShoePrice() {
        return shoePrice;
    }

    public void setShoePrice(String shoePrice) {
        this.shoePrice = shoePrice;
    }

    public int getShoeSales() {
        return shoeSales;
    }

    public void setShoeSales(int shoeSales) {
        this.shoeSales = shoeSales;
    }


}
