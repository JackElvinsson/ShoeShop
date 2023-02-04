package Customer;

import javax.xml.namespace.QName;

public class Location {

    private int locationID;
    private String locationName;

    public Location(int locationID, String locationName) {
        this.locationID = locationID;
        this.locationName= locationName;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }
}
