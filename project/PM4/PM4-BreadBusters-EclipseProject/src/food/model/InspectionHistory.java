package food.model;

import java.util.Date;

/**
 *
 */
public class InspectionHistory {
    protected int InspectionHistoryKey;
    protected String EstablishmentName;
    protected Date InspectionDate;
    protected String InspectionResult;
    protected FoodEstablishments FoodEstablishment;
    

    public InspectionHistory(int inspectionHistoryKey, String establishmentName, Date inspectionDate, String inspectionResult, FoodEstablishments foodEstablishment) {
        this.InspectionHistoryKey = inspectionHistoryKey;
        this.EstablishmentName = establishmentName;
        this.InspectionDate = inspectionDate;
        this.InspectionResult = inspectionResult;
        this.FoodEstablishment = foodEstablishment;
    }

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
