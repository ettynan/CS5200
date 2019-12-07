package food.model;

/**
 * Represents FoodLicenses.
 * @author Clara Mae Wells
 */
public class FoodLicenses {
	protected int foodLicenseKey;
	protected int licenseFK;
	protected String licenseNumber;

	/**
	 * Constructor with all fields for reading records from MySQL.
	 * 
	 * @param foodLicenseKey FoodLicense's primary key
	 * @param licenseFK FoodLicense's foreign key's (Licenses) primary key
	 * @param licenseNumber License's assigned number
	 */
	public FoodLicenses(int foodLicenseKey, int licenseFK, String licenseNumber) {
		this.foodLicenseKey = foodLicenseKey;
		this.licenseFK = licenseFK;
		this.licenseNumber = licenseNumber;
	}
	
	/**
	 * Constructor for reading records from MySQL.
	 * Given the foodLicenseKey primary key,
	 * fetch the full FoodLicense record.
	 * 
	 * @param foodLicenseKey FoodLicense's primary key
	 */
	public FoodLicenses(int foodLicenseKey) {
	}

	/**
	 * @return the foodLicenseKey
	 */
	public int getFoodLicenseKey() {
		return foodLicenseKey;
	}

	/**
	 * @param foodLicenseKey the foodLicenseKey to set
	 */
	public void setFoodLicenseKey(int foodLicenseKey) {
		this.foodLicenseKey = foodLicenseKey;
	}

	/**
	 * @return the licenseFK
	 */
	public int getLicenseFK() {
		return licenseFK;
	}

	/**
	 * @param licenseFK the licenseFK to set
	 */
	public void setLicenseFK(int licenseFK) {
		this.licenseFK = licenseFK;
	}

	/**
	 * @return the licenseNumber
	 */
	public String getLicenseNumber() {
		return licenseNumber;
	}

	/**
	 * @param licenseNumber the licenseNumber to set
	 */
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
}