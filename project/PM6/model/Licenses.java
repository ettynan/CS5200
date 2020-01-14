package food.model;

import java.util.Date;

/**
 * Represents Licenses.
 * @author Clara Mae Wells
 */
public class Licenses {
	protected int licenseKey;
	protected String licenseNumber;
	protected Date licenseIssue;
	protected Date licenseExpiration;
	protected LicenseStatus licenseStatus;
	protected String licenseCategoryFK;
	
	/**
	 * Enumeration of license statuses.
	 * @author Clara Mae Wells
	 *
	 */
	public enum LicenseStatus {
		Active, Inactive
	}

	/**
	 * Constructor with all fields for reading records from MySQL.
	 * 
	 * @param licenseKey License's primary key
	 * @param licenseNumber License's assigned number
	 * @param licenseIssue License's issue date
	 * @param licenseExpiration License's expiration date
	 * @param licenseStatus License's active status
	 * @param licenseCategoryFK License's category foreign key
	 */
	public Licenses(int licenseKey, String licenseNumber, Date licenseIssue, Date licenseExpiration,
			LicenseStatus licenseStatus, String licenseCategoryFK) {
		this.licenseKey = licenseKey;
		this.licenseNumber = licenseNumber;
		this.licenseIssue = licenseIssue;
		this.licenseExpiration = licenseExpiration;
		this.licenseStatus = licenseStatus;
		this.licenseCategoryFK = licenseCategoryFK;
	}
	
	/**
	 * Constructor for reading records from MySQL.
	 * Given the licenseKey primary key, fetch the full Licenses record.
	 * 
	 * @param licenseKey License's synthetic primary key
	 */
	public Licenses(int licenseKey) {
		this.licenseKey = licenseKey;
	}

	/**
	 * @return the licenseKey
	 */
	public int getLicenseKey() {
		return licenseKey;
	}

	/**
	 * @param licenseKey the licenseKey to set
	 */
	public void setLicenseKey(int licenseKey) {
		this.licenseKey = licenseKey;
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

	/**
	 * @return the licenseIssue
	 */
	public Date getLicenseIssue() {
		return licenseIssue;
	}

	/**
	 * @param licenseIssue the licenseIssue to set
	 */
	public void setLicenseIssue(Date licenseIssue) {
		this.licenseIssue = licenseIssue;
	}

	/**
	 * @return the licenseExpiration
	 */
	public Date getLicenseExpiration() {
		return licenseExpiration;
	}

	/**
	 * @param licenseExpiration the licenseExpiration to set
	 */
	public void setLicenseExpiration(Date licenseExpiration) {
		this.licenseExpiration = licenseExpiration;
	}

	/**
	 * @return the licenseStatus
	 */
	public LicenseStatus getLicenseStatus() {
		return licenseStatus;
	}

	/**
	 * @param licenseStatus the licenseStatus to set
	 */
	public void setLicenseStatus(LicenseStatus licenseStatus) {
		this.licenseStatus = licenseStatus;
	}
	
	/**
	 * @return the licenseCategoryFK
	 */
	public String getLicenseCategoryFK() {
		return licenseCategoryFK;
	}

	/**
	 * @param licenseCategoryFK the licenseCategoryFK to set
	 */
	public void setLicenseCategoryFK(String licenseCategoryFK) {
		this.licenseCategoryFK = licenseCategoryFK;
	}
}