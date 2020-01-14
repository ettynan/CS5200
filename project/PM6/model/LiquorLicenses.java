package food.model;

/**
 * Represents FoodLicenses.
 * @author Clara Mae Wells
 */
public class LiquorLicenses {
	protected int liquorLicenseKey;
	protected String liquorLicenseComments;
	protected String liquorLocationComments;
	protected int licenseFK;
	protected String licenseNumber;
	
	/**
	 * Constructor with all fields for reading records from MySQL. 
	 * 
	 * @param liquorLicenseKey LiquorLicense's primary key
	 * @param liquorLicenseComments comments related to LiquorLicense
	 * @param liquorLocationComments comment related to location of LiquorLicense
	 * @param licenseFK LiquorLicense's foreign key's (Licenses) primary key field
	 * @param licenseNumber License's assigned number
	 */
	public LiquorLicenses(int liquorLicenseKey, String liquorLicenseComments, String liquorLocationComments, int licenseFK, String licenseNumber) {
		this.liquorLicenseKey = liquorLicenseKey;
		this.liquorLicenseComments = liquorLicenseComments;
		this.liquorLocationComments = liquorLocationComments;
		this.licenseFK = licenseFK;
		this.licenseNumber = licenseNumber;
	}
	
	/**
	 * Constructor for reading records from MySQL.
	 * Given the liquorLicenseKey primary key,
	 * fetch the full LiquorLicense record.
	 * 
	 * @param liquorLicenseKey LiquorLicense's primary key
	 */
	public LiquorLicenses(int liquorLicenseKey) {
	}

	/**
	 * @return the liquorLicenseKey
	 */
	public int getLiquorLicenseKey() {
		return liquorLicenseKey;
	}

	/**
	 * @param liquorLicenseKey the liquorLicenseKey to set
	 */
	public void setLiquorLicenseKey(int liquorLicenseKey) {
		this.liquorLicenseKey = liquorLicenseKey;
	}

	/**
	 * @return the liquorLicenseComments
	 */
	public String getLiquorLicenseComments() {
		return liquorLicenseComments;
	}

	/**
	 * @param liquorLicenseComments the liquorLicenseComments to set
	 */
	public void setLiquorLicenseComments(String liquorLicenseComments) {
		this.liquorLicenseComments = liquorLicenseComments;
	}

	/**
	 * @return the liquorLocationComments
	 */
	public String getLiquorLocationComments() {
		return liquorLocationComments;
	}

	/**
	 * @param liquorLocationComments the liquorLocationComments to set
	 */
	public void setLiquorLocationComments(String liquorLocationComments) {
		this.liquorLocationComments = liquorLocationComments;
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