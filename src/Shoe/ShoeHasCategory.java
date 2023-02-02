package Shoe;

public class ShoeHasCategory {

    //FK
    private int shoeHasCategory_shoeID;
    private int shoeHasCategory_categoryID;

    public ShoeHasCategory(int shoeHasCategory_shoeID, int shoeHasCategory_categoryID) {
        this.shoeHasCategory_shoeID = shoeHasCategory_shoeID;
        this.shoeHasCategory_categoryID = shoeHasCategory_categoryID;
    }

    public int getShoeHasCategory_shoeID() {
        return shoeHasCategory_shoeID;
    }

    public void setShoeHasCategory_shoeID(int shoeHasCategory_shoeID) {
        this.shoeHasCategory_shoeID = shoeHasCategory_shoeID;
    }

    public int getShoeHasCategory_categoryID() {
        return shoeHasCategory_categoryID;
    }

    public void setShoeHasCategory_categoryID(int shoeHasCategory_categoryID) {
        this.shoeHasCategory_categoryID = shoeHasCategory_categoryID;
    }
}
