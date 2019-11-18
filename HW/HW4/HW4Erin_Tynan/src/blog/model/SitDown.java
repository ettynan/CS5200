package blog.model;

/**
 * Class for SitDown restaurants extending Restaurants
 */
public class SitDown extends Restaurants {
    protected int Capacity;

    public SitDown(int restaurantId, String name, String description, String menu, String hours,
                              boolean active, CuisineType cuisine, String street1, String street2,
                              String city, String state, int zip, String companyName, int capacity) {
        super(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip,
                companyName);
        this.Capacity = capacity;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        this.Capacity = capacity;
    }
}

