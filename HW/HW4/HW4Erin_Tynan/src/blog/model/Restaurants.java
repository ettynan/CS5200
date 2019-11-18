package blog.model;

/**
 * Abstract class Restaurants has 3 concrete classes of restaurant
 */
public class Restaurants {
    protected int RestaurantId;
    protected String Name;
    protected String Description;
    protected String Menu;
    protected String Hours;
    protected boolean Active;
    protected CuisineType Cuisine;
    protected String Street1;
    protected String Street2;
    protected String City;
    protected String State;
    protected int Zip;
    protected String CompanyName;

    public enum CuisineType {
	    AFRICAN,
	    AMERICAN,
	    ASIAN,
	    EUROPEAN,
	    HISPANIC
	}
    public Restaurants(int restaurantId, String name, String description, String menu, String hours,
                       boolean active, CuisineType cuisine, String street1, String street2,
                       String city, String state, int zip, String companyName) {
        this.RestaurantId = restaurantId;
        this.Name = name;
        this.Description = description;
        this.Menu = menu;
        this.Hours = hours;
        this.Active = active;
        this.Cuisine = cuisine;
        this.Street1 = street1;
        this.Street2 = street2;
        this.City = city;
        this.State = state;
        this.Zip = zip;
        this.CompanyName = companyName;
    }

    public int getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.RestaurantId = restaurantId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getMenu() {
        return Menu;
    }

    public void setMenu(String menu) {
        this.Menu = menu;
    }

    public String getHours() {
        return Hours;
    }

    public void setHours(String hours) {
        this.Hours = hours;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        this.Active = active;
    }

    public CuisineType getCuisine() {
        return Cuisine;
    }

    public void setCuisine(CuisineType cuisine) {
        this.Cuisine = cuisine;
    }

    public String getStreet1() {
        return Street1;
    }

    public void setStreet1(String street1) {
        this.Street1 = street1;
    }

    public String getStreet2() {
        return Street2;
    }

    public void setStreet2(String street2) {
        this.Street2 = street2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        this.State = state;
    }

    public int getZip() {
        return Zip;
    }

    public void setZip(int zip) {
        this.Zip = zip;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompany(String companyName) {
        this.CompanyName = companyName;
    }
}
