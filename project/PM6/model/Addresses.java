package food.model;



/**
 * Addresses is a simple, plain old java objects (POJO).
 */
public class Addresses{
	protected int establishmentKey;
	protected String street;
	protected String city;
	protected String state;
	protected int zip;
	protected int propertyId;
	
	
	public Addresses(int establishmentKey, String street, String city, String state, int zip, int propertyId) {
		this.establishmentKey = establishmentKey;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.propertyId = propertyId;
	}
	
	public Addresses(int establishmentKey) {
		this.establishmentKey = establishmentKey;
	}
	

	/** Getters and setters. */
	
	public int getEstablishmentKey() {
		return establishmentKey;
	}

	public void setEstablishmentKey(int establishmentKey) {
		this.establishmentKey = establishmentKey;
	}	
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}
	
}
