package food.model;

import java.util.Date;

/**
 * InspectionHistory class encompassing inspections for food establishments.
 */
public class InspectionHistory {
    protected int InspectionHistoryKey;
    protected String EstablishmentName;
    protected Date InspectionDate;
    protected String InspectionResult;
    protected FoodEstablishments FoodEstablishment;
    
    /**
	 * Constructor with all fields for inspection records from MySQL. Includes auto-generated primary key for creation of inspection
	 * 
	 * @param inspectionHistoryKey InspectionHistory primary key
	 * @param establishmentName InspectionHistory name of the establishment the inspection is for
	 * @param inspectionDate InspectionHistory's date of inspection
	 * @param inspectionResult InspectionHistory's result of inspection
	 * @param foodEstablishment InspectionHistory's foreign key
	 */
    public InspectionHistory(int inspectionHistoryKey, String establishmentName, Date inspectionDate, String inspectionResult, FoodEstablishments foodEstablishment) {
        this.InspectionHistoryKey = inspectionHistoryKey;
        this.EstablishmentName = establishmentName;
        this.InspectionDate = inspectionDate;
        this.InspectionResult = inspectionResult;
        this.FoodEstablishment = foodEstablishment;
    }

    /**
	 * Constructor with all fields for reading inspection records from MySQL. Using the Inspection Key to find the full record
	 * 
	 * @param establishmentName InspectionHistory name of the establishment the inspection is for
	 * @param inspectionDate InspectionHistory's date
	 * @param inspectionResult InspectionHistory's result of inspection
	 * @param foodEstablishment InspectionHistory's foreign key
	 */
    public InspectionHistory(String establishmentName, Date inspectionDate, String inspectionResult, FoodEstablishments foodEstablishment) {
        this.EstablishmentName = establishmentName;
        this.InspectionDate = inspectionDate;
        this.InspectionResult = inspectionResult;
        this.FoodEstablishment = foodEstablishment;
    }
 
    public InspectionHistory(String establishmentName) {
    	this.EstablishmentName = establishmentName;
	}

	public InspectionHistory(int inspectionHistoryKey) {
		this.InspectionHistoryKey = inspectionHistoryKey;
	}

	public int getInspectionHistoryKey() { 
        return InspectionHistoryKey;
    }

    public void setInspectionHistoryKey(int inspectionHistoryKey) {
        this.InspectionHistoryKey = inspectionHistoryKey;
    }

    public String getEstablishmentName() {
        return EstablishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.EstablishmentName = establishmentName;
    }

    public Date getInspectionDate() {
        return InspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.InspectionDate = inspectionDate;
    }

    public String getInspectionResult() {
        return InspectionResult;
    }

    public void setInspectionResult(String inspectionResult) {
        this.InspectionResult = inspectionResult;
    }

    public FoodEstablishments getFoodEstablishment() {
        return FoodEstablishment;
    }

    public void setFoodEstablishment(FoodEstablishments foodEstablishment) {
        this.FoodEstablishment = foodEstablishment;
    }

}
