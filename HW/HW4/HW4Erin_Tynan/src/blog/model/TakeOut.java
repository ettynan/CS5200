package blog.model;

/**
 * Class for TakeOut restaurants extending Restaurants
 */
public class TakeOut extends Restaurants {
    protected int MaxWaitTime;

    public TakeOut(int restaurantId, String name, String description, String menu, String hours,
                              boolean active, CuisineType cuisine, String street1, String street2,
                              String city, String state, int zip, String companyName, int maxWaitTime) {
        super(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip,
                companyName);
        this.MaxWaitTime = maxWaitTime;
    }

    public int getMaxWaitTime() {
        return MaxWaitTime;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.MaxWaitTime = maxWaitTime;
    }
}
