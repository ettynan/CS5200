package food.model;

import java.util.Date;

/**
 *  ViolationHistory for each food establishment
 */
public class ViolationHistory {
    protected int ViolationHistoryKey;
    protected String EstablishmentName;
    protected String ViolationCode;
    protected String ViolationLevel;
    protected String ViolationDescription;
    protected Date ViolationDate;
    protected String ViolationStatus;
    protected String ViolationComments;
    protected FoodEstablishments FoodEstablishment;
    protected InspectionHistory InspectionHistory;

    public ViolationHistory(int violationHistoryKey, String establishmentName, String violationCode,
                            String violationLevel, String violationDescription, Date violationDate,
                            String violationStatus, String violationComments, FoodEstablishments foodEstablishment,
                            InspectionHistory inspectionHistory) {
        this.ViolationHistoryKey = violationHistoryKey;
        this.EstablishmentName = establishmentName;
        this.ViolationCode = violationCode;
        this.ViolationLevel = violationLevel;
        this.ViolationDescription = violationDescription;
        this.ViolationDate = violationDate;
        this.ViolationStatus = violationStatus;
        this.ViolationComments = violationComments;
        this.FoodEstablishment = foodEstablishment;
        this.InspectionHistory = inspectionHistory;
    }
    
    public ViolationHistory(String establishmentName, String violationCode,
            String violationLevel, String violationDescription, Date violationDate,
            String violationStatus, String violationComments, FoodEstablishments foodEstablishment,
            InspectionHistory inspectionHistory) {
		this.EstablishmentName = establishmentName;
		this.ViolationCode = violationCode;
		this.ViolationLevel = violationLevel;
		this.ViolationDescription = violationDescription;
		this.ViolationDate = violationDate;
		this.ViolationStatus = violationStatus;
		this.ViolationComments = violationComments;
		this.FoodEstablishment = foodEstablishment;
		this.InspectionHistory = inspectionHistory;
}

    public ViolationHistory(String establishmentName) {
		this.EstablishmentName = establishmentName;
	}

	public ViolationHistory(int violationHistoryKey) {
        this.ViolationHistoryKey = violationHistoryKey;
	}

	public int getViolationHistoryKey() {
        return ViolationHistoryKey;
    }

    public void setViolationHistoryKey(int violationHistoryKey) {
        this.ViolationHistoryKey = violationHistoryKey;
    }

    public String getEstablishmentName() {
        return EstablishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.EstablishmentName = establishmentName;
    }

    public String getViolationCode() {
        return ViolationCode;
    }

    public void setViolationCode(String violationCode) {
        this.ViolationCode = violationCode;
    }

    public String getViolationLevel() {
        return ViolationLevel;
    }

    public void setViolationLevel(String violationLevel) {
        this.ViolationLevel = violationLevel;
    }

    public String getViolationDescription() {
        return ViolationDescription;
    }

    public void setViolationDescription(String violationDescription) {
        this.ViolationDescription = violationDescription;
    }

    public Date getViolationDate() {
        return ViolationDate;
    }

    public void setViolationDate(Date violationDate) {
        this.ViolationDate = violationDate;
    }

    public String getViolationStatus() {
        return ViolationStatus;
    }

    public void setViolationStatus(String violationStatus) {
        this.ViolationStatus = violationStatus;
    }

    public String getViolationComments() {
        return ViolationComments;
    }

    public void setViolationComments(String violationComments) {
        this.ViolationComments = violationComments;
    }

    public FoodEstablishments getFoodEstablishment() {
        return FoodEstablishment;
    }

    public void setFoodEstablishment(FoodEstablishments foodEstablishment) {
        this.FoodEstablishment = foodEstablishment;
    }

    public InspectionHistory getInspectionHistory() {
        return InspectionHistory;
    }

    public void setInspectionHistory(InspectionHistory inspectionHistory) {
        this.InspectionHistory = inspectionHistory;
    }
}
