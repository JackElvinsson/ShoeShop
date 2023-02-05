package Shoe;

public class Shoe {

    private int shoeID;
    private int inventory;
    private String shoeColor;
    private int shoeSize;
    private double shoePrice;
    private int shoeSales;
    private Brand brand;
    private Model model;

    public Shoe() {
    }

    public Shoe(int shoeID, int inventory, String shoeColor, int shoeSize, double shoePrice, int shoeSales) {
        this.shoeID = shoeID;
        this.inventory = inventory;
        this.shoeColor = shoeColor;
        this.shoeSize = shoeSize;
        this.shoePrice = shoePrice;
        this.shoeSales = shoeSales;
    }


    public double getShoePrice() {
        return shoePrice;
    }

    public void setShoePrice(double shoePrice) {
        this.shoePrice = shoePrice;
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



    public int getShoeSales() {
        return shoeSales;
    }

    public void setShoeSales(int shoeSales) {
        this.shoeSales = shoeSales;
    }


}
