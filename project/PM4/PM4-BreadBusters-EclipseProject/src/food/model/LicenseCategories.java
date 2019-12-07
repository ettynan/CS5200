package food.model;

/**
 * Represents Categories of Licenses.
 * @author Clara Mae Wells
 */
public class LicenseCategories {
	protected String licenseCategory;
	protected String licenseCategoryDescription;
	
	/**
	 * Constructor with all fields for reading records from MySQL or creating new records.
	 * 
	 * @param licenseCategory category of the license issued
	 * @param licenseCategoryDescription description of the license category
	 */
	public LicenseCategories(String licenseCategory, String licenseCategoryDescription) {
		this.licenseCategory = licenseCategory;
		this.licenseCategoryDescription = licenseCategoryDescription;
	}
	
	/**
	 * Constructor for reading records from MySQL.
	 * Given the licenseCategory primary key, fetch the full LicenseCategories record.
	 * 
	 * @param licenseCategory category of the license issued
	 */
	public LicenseCategories(String licenseCategory) {
		this.licenseCategory = licenseCategory;
	}

	/**
	 * @return the licenseCategory
	 */
	public String getLicenseCategory() {
		return licenseCategory;
	}

	/**
	 * @param licenseCategory the licenseCategory to set
	 */
	public void setLicenseCategory(String licenseCategory) {
		this.licenseCategory = licenseCategory;
	}

	/**
	 * @return the licenseCategoryDescription
	 */
	public String getLicenseCategoryDescription() {
		return licenseCategoryDescription;
	}

	/**
	 * @param licenseCategoryDescription the licenseCategoryDescription to set
	 */
	public void setLicenseCategoryDescription(String licenseCategoryDescription) {
		this.licenseCategoryDescription = licenseCategoryDescription;
	}
}