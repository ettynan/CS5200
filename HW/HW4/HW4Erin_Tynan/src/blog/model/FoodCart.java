package blog.model;

/**
 * Class for FoodCart restaurants extending Restaurants
 */
public class FoodCart extends Restaurants {
    protected boolean Licensed;

    public FoodCart(int restaurantId, String name, String description, String menu, String hours,
                               boolean active, CuisineType cuisine, String street1, String street2,
                               String city, String state, int zip, String companyName, boolean licensed) {
        super(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip,
                companyName);
        this.Licensed = licensed;
    }

    public boolean isLicensed() {
        return Licensed;
    }

    public void setLicensed(boolean licensed) {
        this.Licensed = licensed;
    }
}
