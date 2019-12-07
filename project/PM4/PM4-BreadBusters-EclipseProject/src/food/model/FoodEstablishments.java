package food.model;



/**
 * Administrators is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class FoodEstablishments{
	protected int foodEstablishmentKey;
	protected int establishmentFK;
	protected String establishmentName;
	
	
	public FoodEstablishments(int foodEstablishmentKey, int establishmentFK, String establishmentName) {
		this.foodEstablishmentKey = foodEstablishmentKey;
		this.establishmentFK = establishmentFK;
		this.establishmentName = establishmentName;
	}
	
	public FoodEstablishments(int establishmentFK, String establishmentName) {
		this.establishmentFK = establishmentFK;
		this.establishmentName = establishmentName;
	}
	
	public FoodEstablishments(String establishmentName) {
		this.establishmentName = establishmentName;
	}
	
	public FoodEstablishments(int foodEstablishmentKey) {
		this.foodEstablishmentKey = foodEstablishmentKey;
	}

	/** Getters and setters. */
	
	public int getFoodEstablishmentKey() {
		return foodEstablishmentKey;
	}

	public void setFoodEstablishmentKey(int foodEstablishmentKey) {
		this.foodEstablishmentKey = foodEstablishmentKey;
	}	
	
	public int getEstablishmentFK() {
		return establishmentFK;
	}

	public void setEstablishmentFK(int establishmentFK) {
		this.establishmentFK = establishmentFK;
	}
	
	public String getEstablishmentName() {
		return establishmentName;
	}

	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}
	
	
}
