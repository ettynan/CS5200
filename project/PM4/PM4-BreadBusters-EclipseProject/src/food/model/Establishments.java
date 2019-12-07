package food.model;



/**
 * Administrators is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class Establishments{
	protected int establishmentKey;
	protected String establishmentName;
	protected String establishmentOwner;
	protected int licenseFK;
	
	
	public Establishments(int establishmentKey, String establishmentName, String establishmentOwner, int licenseFK) {
		this.establishmentKey = establishmentKey;
		this.establishmentName = establishmentName;
		this.establishmentOwner = establishmentOwner;
		this.licenseFK = licenseFK;
	}
	
	public Establishments(String establishmentName) {
		this.establishmentName = establishmentName;
	}
	
	public Establishments(int establishmentKey) {
		this.establishmentKey = establishmentKey;
	}

	/** Getters and setters. */
	
	public int getEstablishmentKey() {
		return establishmentKey;
	}

	public void setEstablishmentKey(int establishmentKey) {
		this.establishmentKey = establishmentKey;
	}	
	
	public String getEstablishmentName() {
		return establishmentName;
	}

	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}
	
	public String getEstablishmentOwner() {
		return establishmentOwner;
	}

	public void setEstablishmentOwner(String establishmentOwner) {
		this.establishmentOwner = establishmentOwner;
	}
	
	public int getLicenseFK() {
		return licenseFK;
	}

	public void setLicenseFK(int licenseFK) {
		this.licenseFK = licenseFK;
	}
	
}
