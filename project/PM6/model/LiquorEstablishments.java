package food.model;



/**
 * LiquorEstablishments is a simple, plain old java objects (POJO).
 */
public class LiquorEstablishments{
	protected int liquorEstablishmentKey;
	protected int establishmentFK;
	protected String liquorEstablishmentComments;
	protected String timeClose;
	protected String timePatronsOffPremises;
	protected int capacity;
	
	
	public LiquorEstablishments(int liquorEstablishmentKey, int establishmentFK, String liquorEstablishmentComments, String timeClose,
			String timePatronsOffPremises, int capacity) {
		this.liquorEstablishmentKey = liquorEstablishmentKey;
		this.establishmentFK = establishmentFK;
		this.liquorEstablishmentComments = liquorEstablishmentComments;
		this.timeClose = timeClose;
		this.timePatronsOffPremises = timePatronsOffPremises;
		this.capacity = capacity;
	}
	
	public LiquorEstablishments(int establishmentFK, String liquorEstablishmentComments, String timeClose,
			String timePatronsOffPremises, int capacity) {
		this.establishmentFK = establishmentFK;
		this.liquorEstablishmentComments = liquorEstablishmentComments;
		this.timeClose = timeClose;
		this.timePatronsOffPremises = timePatronsOffPremises;
		this.capacity = capacity;
	}
	
	public LiquorEstablishments(int liquorEstablishmentKey) {
		this.liquorEstablishmentKey = liquorEstablishmentKey;
	}
	
	public LiquorEstablishments(int liquorEstablishmentKey, int establishmentFK) {
		this.liquorEstablishmentKey = liquorEstablishmentKey;
		this.establishmentFK = establishmentFK;
	}

	/** Getters and setters. */
	
	public int getLiquorEstablishmentKey() {
		return liquorEstablishmentKey;
	}
	
	public void setLiquorEstablishmentKey(int liquorEstablishmentKey) {
		this.liquorEstablishmentKey = liquorEstablishmentKey;
	}
	
	public int getEstablishmentFK() {
		return establishmentFK;
	}

	public void setEstablishmentFK(int establishmentFK) {
		this.establishmentFK = establishmentFK;
	}	
	
	public String getComments() {
		return liquorEstablishmentComments;
	}

	public void setComments(String liquorEstablishmentComments) {
		this.liquorEstablishmentComments = liquorEstablishmentComments;
	}
	
	public String getTimeClose() {
		return timeClose;
	}

	public void setTimeClose(String timeClose) {
		this.timeClose = timeClose;
	}
	
	public String getTimePatronsOffPremises() {
		return timePatronsOffPremises;
	}

	public void setTimePatronsOffPremises(String timePatronsOffPremises) {
		this.timePatronsOffPremises = timePatronsOffPremises;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
