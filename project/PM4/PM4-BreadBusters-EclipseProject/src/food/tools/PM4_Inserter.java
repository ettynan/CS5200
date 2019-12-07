package food.tools;

import food.dal.*;
import food.model.*;
import food.model.Licenses.LicenseStatus;
import food.model.Weather.Events;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class PM4_Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		WeatherDao weatherDao = WeatherDao.getInstance();
		LicenseCategoriesDao licenseCategoriesDao = LicenseCategoriesDao.getInstance();
		LicensesDao licensesDao = LicensesDao.getInstance();
		FoodLicensesDao foodLicensesDao = FoodLicensesDao.getInstance();
		LiquorLicensesDao liquorLicensesDao = LiquorLicensesDao.getInstance();
		EstablishmentsDao establishmentsDao = EstablishmentsDao.getInstance();
		AddressesDao addressesDao = AddressesDao.getInstance();
		LiquorEstablishmentsDao liquorEstablishmentsDao = LiquorEstablishmentsDao.getInstance();
		FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
		InspectionHistoryDao inspectionHistoryDao = InspectionHistoryDao.getInstance();
		ViolationHistoryDao violationHistoryDao = ViolationHistoryDao.getInstance();

		Date date = new Date();
	/*	
		Weather W1 = new Weather(1, date, 45, 40, 27, 80, 70, 55, 20, 2, 26, 0, 0 ,Events.None);
		W1 = weatherDao.create(W1);
		Weather W2 = new Weather(2, date, 45, 40, 27, 80, 70, 55, 20, 2, 26, 0, 0 ,Events.None);
		W2 = weatherDao.create(W2);
		Weather W3 = new Weather(3, date, 45, 40, 27, 80, 70, 55, 20, 2, 26, 0, 0 ,Events.Both);
		W3 = weatherDao.create(W3);
		
		LicenseCategories LC1 = new LicenseCategories("FC", "The Best Cat"); 
		LC1 = licenseCategoriesDao.create(LC1);
		LicenseCategories LC2 = new LicenseCategories("LC", "The second Best Cat");
		LC2 = licenseCategoriesDao.create(LC2);
		*/
		Licenses L1 = new Licenses(8179, "12345", date, date, LicenseStatus.Active, "FT");
		L1 = licensesDao.create(L1);
		Licenses L2 = new Licenses(8180, "67890", date, date, LicenseStatus.Inactive, "FT");
		L2 = licensesDao.create(L2);
		Licenses L3 = new Licenses(8181, "67894", date, date, LicenseStatus.Active, "FT");
		L3 = licensesDao.create(L3);
		Licenses L4 = new Licenses(8182, "67895", date, date, LicenseStatus.Active, "FT");
		L4 = licensesDao.create(L4);
		Licenses L5 = new Licenses(8183, "67896", date, date, LicenseStatus.Active, "FT");
		L5 = licensesDao.create(L5);
	/*	
		/*
		LiquorLicenses LL1 = new LiquorLicenses(1, "LicenseComment", "LocationComment", 3, "123123", date, date, LicenseStatus.Active, "LC");
		LL1 = liquorLicensesDao.create(LL1);
		LiquorLicenses LL2 = new LiquorLicenses(2, "LicenseComment", "LocationComment", 4, "456456", date, date, LicenseStatus.Active, "LC");
		LL2 = liquorLicensesDao.create(LL2);
		
		FoodLicenses FL1 = new FoodLicenses(1, 5, "121212", date, date, LicenseStatus.Active, "FC");
		FL1 = foodLicensesDao.create(FL1);
		FoodLicenses FL2 = new FoodLicenses(1, 6, "121212", date, date, LicenseStatus.Active, "FC");
		FL2 = foodLicensesDao.create(FL2);
		
		Establishments E1 = new Establishments(8179, "Establishment1", "Owner1", 1);
		E1 = establishmentsDao.create(E1);
		Establishments E2 = new Establishments(8180, "Establishment2", "Owner1", 2);
		E2 = establishmentsDao.create(E2);
		Establishments E3 = new Establishments(8181, "Establishment3", "Owner2", 3);
		E3 = establishmentsDao.create(E3);
		Establishments E4 = new Establishments(8182, "Establishment4", "Owner3", 4);
		E4 = establishmentsDao.create(E4);
		
		Addresses A1 = new Addresses(8179, "West Street", "Boston", "MA", 2118, 12345);
		A1 = addressesDao.create(A1);
		Addresses A2 = new Addresses(8180, "East Street", "Boston", "MA", 2110, 67890);
		A2 = addressesDao.create(A2);
		Addresses A3 = new Addresses(8181, "North Street", "Boston", "MA", 2110, 12312);
		A3 = addressesDao.create(A3);
		Addresses A4 = new Addresses(8182, "North Street", "Boston", "MA", 2110, 12312);
		A4 = addressesDao.create(A4);
		
		LiquorEstablishments LE1 = new LiquorEstablishments(1110, 8180, "Comments", "11:00PM", "11:30PM", 30);
		LE1 = liquorEstablishmentsDao.create(LE1);
		
		
		FoodEstablishments FE1 = new FoodEstablishments(7070,8179,"Establishment1");
		FE1 = foodEstablishmentsDao.create(FE1);
		FoodEstablishments FE2 = new FoodEstablishments(2,3,"Establishment3");
		FE2 = foodEstablishmentsDao.create(FE2);
		
		InspectionHistory IH1 = new InspectionHistory(1, "West Street", date, "HE_Pass", FE1);
		IH1 = inspectionHistoryDao.create(IH1);
		InspectionHistory IH2 = new InspectionHistory(2, "West Street", date, "HE_Fail", FE1);
		IH2 = inspectionHistoryDao.create(IH2);
		InspectionHistory IH3 = new InspectionHistory(3, "East Street", date, "HE_Pass", FE2);
		IH3 = inspectionHistoryDao.create(IH3);
		InspectionHistory IH4 = new InspectionHistory(4, "East Street", date, "HE_Fail", FE2);
		IH4 = inspectionHistoryDao.create(IH4);
		
		ViolationHistory VH1 = new ViolationHistory(1, "West Street", "14-2-48/32", "*", "Description is here", date, ViolationStatus.Pass, "Comments Here", FE1,IH1);
		VH1 = violationHistoryDao.create(VH1);
		ViolationHistory VH2 = new ViolationHistory(2, "West Street", "12-2-48/32", "*", "Description is here", date, ViolationStatus.Pass, "Comments Here", FE1,IH1);
		VH2 = violationHistoryDao.create(VH2);
		ViolationHistory VH3 = new ViolationHistory(3, "West Street", "10-2-48/32", "**", "Description is here", date, ViolationStatus.Fail, "Comments Here", FE1,IH2);
		VH3 = violationHistoryDao.create(VH3);
		ViolationHistory VH4 = new ViolationHistory(4, "West Street", "10-2-48/32", "***", "Description is here", date, ViolationStatus.Fail, "Comments Here", FE1,IH2);
		VH4 = violationHistoryDao.create(VH4);
		ViolationHistory VH5 = new ViolationHistory(5, "East Street", "11-2-48/32", "*", "Description is here", date, ViolationStatus.Pass, "Comments Here", FE2,IH3);
		VH5 = violationHistoryDao.create(VH5);
		ViolationHistory VH6 = new ViolationHistory(6, "East Street", "11-2-48/32", "*", "Description is here", date, ViolationStatus.Pass, "Comments Here", FE2,IH3);
		VH6 = violationHistoryDao.create(VH6);
		ViolationHistory VH7 = new ViolationHistory(7, "East Street", "10-2-48/32", "***", "Description is here", date, ViolationStatus.Fail, "Comments Here", FE2,IH4);
		VH7 = violationHistoryDao.create(VH7);
		ViolationHistory VH8 = new ViolationHistory(8, "East Street", "10-2-48/32", "**", "Description is here", date, ViolationStatus.Fail, "Comments Here", FE2,IH4);
		VH8 = violationHistoryDao.create(VH8);
		
		
				
		// READ. 
		//Weather
		Weather w = weatherDao.getWeatherByWeatherKey(1);
		List<Weather> wByDate = weatherDao.getWeatherByWeatherDate(date);
		List<Weather> wByEvent = weatherDao.getWeatherByWeatherEvents(Weather.Events.None);
		System.out.format("Read weather: key:%s, date:%s, temphigh:%s, tempav:%s, templow:%s, humhigh:%s, humav:%s, humlow:%s, windhigh:%s, windav:%s, "
				+ "windgusthigh:%s, snowfall:%s, precip:%s, events:%s \n", w.getWeatherKey(), w.getWeatherDate().toString(), w.getTempHighInF(), w.getTempAverageInF(), w.getTempLowInF(),
				w.getHumidityHighPercentage(), w.getHumidityAveragePercentage(), w.getHumidityLowPercentage(), w.getWindHighInMph(), w.getWindAverageInMph(), w.getWindGustHighInMph(),
				w.getSnowFallInInches(), w.getPrecipitationInInches(), w.getEvents().toString());
		for(Weather we : wByDate) {
			System.out.format("Loop through weather by date: key:%s, date:%s, temphigh:%s, tempav:%s, templow:%s, humhigh:%s, humav:%s, humlow:%s, windhigh:%s, windav:%s, "
					+ "windgusthigh:%s, snowfall:%s, precip:%s, events:%s \n", we.getWeatherKey(), we.getWeatherDate().toString(), we.getTempHighInF(), we.getTempAverageInF(), we.getTempLowInF(),
					we.getHumidityHighPercentage(), we.getHumidityAveragePercentage(), we.getHumidityLowPercentage(), we.getWindHighInMph(), we.getWindAverageInMph(), we.getWindGustHighInMph(),
					we.getSnowFallInInches(), we.getPrecipitationInInches(), we.getEvents().toString());
		}
		for(Weather we : wByEvent) {
			System.out.format("Loop through weather by event: key:%s, date:%s, temphigh:%s, tempav:%s, templow:%s, humhigh:%s, humav:%s, humlow:%s, windhigh:%s, windav:%s, "
					+ "windgusthigh:%s, snowfall:%s, precip:%s, events:%s \n", we.getWeatherKey(), we.getWeatherDate().toString(), we.getTempHighInF(), we.getTempAverageInF(), we.getTempLowInF(),
					we.getHumidityHighPercentage(), we.getHumidityAveragePercentage(), we.getHumidityLowPercentage(), we.getWindHighInMph(), we.getWindAverageInMph(), we.getWindGustHighInMph(),
					we.getSnowFallInInches(), we.getPrecipitationInInches(), we.getEvents().toString());
		}
		
		//Establishments
		Establishments ek = establishmentsDao.getEstablishmentByKey(2);
		Establishments en = establishmentsDao.getEstablishmentByName("Establishment1");
		List<Establishments> estabOwner = establishmentsDao.getEstablishmentsByOwner("Owner1");
		System.out.format("Read Estab by Key: key:%s, name:%s, owner:%s, licenseFK:%s \n", ek.getEstablishmentKey(), ek.getEstablishmentName(), ek.getEstablishmentOwner(),
				ek.getLicenseFK());
		System.out.format("Read Estab by Name: key:%s, name:%s, owner:%s, licenseFK:%s \n", en.getEstablishmentKey(), en.getEstablishmentName(), en.getEstablishmentOwner(),
				en.getLicenseFK());
		for(Establishments eo : estabOwner) {
			System.out.format("Loop through Estab by Owner: key:%s, name:%s, owner:%s, licenseFK:%s \n", eo.getEstablishmentKey(), eo.getEstablishmentName(), eo.getEstablishmentOwner(),
					eo.getLicenseFK());
		}
		
		//Addresses
		Addresses ak = addressesDao.getAddressByKey(2);
		Addresses ap = addressesDao.getAddressByPropertyId(12345);
		List<Addresses> addZip = addressesDao.getAddressesByZip(2110);
		System.out.format("Read Address by Key: key:%s, street:%s, city:%s, state:%s, zip:%s, pId:%s \n", ak.getEstablishmentKey(), ak.getStreet(), ak.getCity(), ak.getState(),
				ak.getZip(), ak.getPropertyId());
		System.out.format("Read Address by PropertyId: key:%s, street:%s, city:%s, state:%s, zip:%s, pId:%s \n", ap.getEstablishmentKey(), ap.getStreet(), ap.getCity(), ap.getState(),
				ap.getZip(), ap.getPropertyId());
		for(Addresses az : addZip) {
			System.out.format("Loop through Addresses by Zip: key:%s, street:%s, city:%s, state:%s, zip:%s, pId:%s \n", az.getEstablishmentKey(), az.getStreet(), az.getCity(), az.getState(), 
				az.getZip(), az.getPropertyId());
		}
		
		//LiquorEstablishments
		LiquorEstablishments lek = liquorEstablishmentsDao.getLiquorEstablishmentByKey(2);
		LiquorEstablishments lee = liquorEstablishmentsDao.getLiquorEstablishmentByEstablishmentKey(2);
		System.out.format("Read LiquorEstab by Key: liqKey:%s, estabKey:%s, comments:%s, TimeClose:%s, PatronsOff:%s, capacity:%s \n", lek.getLiquorEstablishmentKey(),
				lek.getEstablishmentFK(), lek.getComments(), lek.getTimeClose(), lek.getTimePatronsOffPremises(), lek.getCapacity());
		System.out.format("Read LiquorEstab by EstabKey: liqKey:%s, estabKey:%s, comments:%s, TimeClose:%s, PatronsOff:%s, capacity:%s \n", lee.getLiquorEstablishmentKey(),
				lee.getEstablishmentFK(), lee.getComments(), lee.getTimeClose(), lee.getTimePatronsOffPremises(), lee.getCapacity());
		
		//FoodEstablishments
		FoodEstablishments fek = foodEstablishmentsDao.getFoodEstablishmentByKey(1);
		FoodEstablishments fee = foodEstablishmentsDao.getFoodEstablishmentByEstablishmentKey(3);
		FoodEstablishments fen = foodEstablishmentsDao.getFoodEstablishmentByName("Establishment1");
		System.out.format("Read FoodEstab by Key: foodKey:%s, estabKey:%s, name:%s \n", fek.getFoodEstablishmentKey(), fek.getEstablishmentFK(), fek.getEstablishmentName());
		System.out.format("Read FoodEstab by EstabKey: foodKey:%s, estabKey:%s, name:%s \n", fee.getFoodEstablishmentKey(), fee.getEstablishmentFK(), fee.getEstablishmentName());
		System.out.format("Read FoodEstab by Name: foodKey:%s, estabKey:%s, name:%s \n", fen.getFoodEstablishmentKey(), fen.getEstablishmentFK(), fen.getEstablishmentName());
	/*
		//UPDATES
		//Addresses
		System.out.format("Address before update: key:%s, street:%s, city:%s, state:%s, zip:%s, pId:%s \n", A2.getEstablishmentKey(), A2.getStreet(), A2.getCity(), A2.getState(),
				A2.getZip(), A2.getPropertyId());
		addressesDao.updateAddress(2, "new street", "new city", "MA", 2118, 12121);
		Addresses updatedA2 = addressesDao.getAddressByKey(2);
		System.out.format("Address after update: key:%s, street:%s, city:%s, state:%s, zip:%s, pId:%s \n", updatedA2.getEstablishmentKey(), updatedA2.getStreet(), updatedA2.getCity(),
				updatedA2.getState(), updatedA2.getZip(), updatedA2.getPropertyId());
		
		//DELETES
		//Addresses
	    addressesDao.deleteByKey(3);
        Addresses adel = addressesDao.getAddressByKey(3);
        if(adel==null) {
            System.out.print("Address3 deleted\n");
        }
        else {
            System.out.print("Address3 not deleted\n");
        }
        
        //LiquorEstablishments
        liquorEstablishmentsDao.deleteByKey(2);
        LiquorEstablishments deleteLE2 = liquorEstablishmentsDao.getLiquorEstablishmentByKey(2);
        if(deleteLE2==null) {
            System.out.print("LiquorEstab2 deleted\n");
        }
        else {
            System.out.print("LiquorEstab2 not deleted\n");
        }
        
        //FoodEstablishments
        foodEstablishmentsDao.deleteByName("Establishment3");
        FoodEstablishments deleteFE2 = foodEstablishmentsDao.getFoodEstablishmentByName("Establishment3");
        if(deleteFE2==null) {
            System.out.print("LiquorEstab2 deleted\n");
        }
        else {
            System.out.print("LiquorEstab2 not deleted\n");
        }
		
        //Establishments
        establishmentsDao.deleteByName("Establishment4");
        Establishments deleteE4 = establishmentsDao.getEstablishmentByName("Establishment4");
        if(deleteE4==null) {
            System.out.print("LiquorEstab2 deleted\n");
        }
        else {
            System.out.print("LiquorEstab2 not deleted\n");
        }
		/*	
		//InsepctionHistory
		InspectionHistory isph1 = inspectionHistoryDao.getInspectionHistoryByISHKey(2);
		System.out.format("Reading InspectionHistory: u:%s f:%s l:%s e:%s p:%s \n",
			isph1.getInspectionHistoryKey(), isph1.getEstablishmentName(), isph1.getInspectionDate(), isph1.getInspectionResult(), 
			isph1.getFoodEstablishment().getFoodEstablishmentKey());
		
		
		InspectionHistory isph2 = inspectionHistoryDao.getInspectionHistoryByFEKey(2);
		System.out.format("Reading InspectionHistory: u:%s f:%s l:%s e:%s p:%s \n",
			isph2.getInspectionHistoryKey(), isph2.getEstablishmentName(), isph2.getInspectionDate(), isph2.getInspectionResult(), 
			isph2.getFoodEstablishmentFK());
		

		List<InspectionHistory> isphList1 = inspectionHistoryDao.getInspectionHistoryByEstablishmentName("Establishment1");
		for(InspectionHistory i : isphList1) {
			System.out.format("Reading InspectionHistory: u:%s f:%s l:%s e:%s p:%s \n",
					i.getInspectionHistoryKey(), i.getEstablishmentName(), i.getInspectionDate(), i.getInspectionResult(), 
					i.getFoodEstablishmentFK());
		}
		
		List<InspectionHistory> isphList2 = inspectionHistoryDao.getInspectionHistoryByInspectionDate(null);
		for(InspectionHistory i : isphList2) {
			System.out.format("Reading InspectionHistory: u:%s f:%s l:%s e:%s p:%s \n",
					i.getInspectionHistoryKey(), i.getEstablishmentName(), i.getInspectionDate(), i.getInspectionResult(), 
					i.getFoodEstablishmentFK());
		}
		
		List<InspectionHistory> isphList3 = inspectionHistoryDao.getInspectionHistoryByInspectionResult("HE_Pass");
		for(InspectionHistory i : isphList3) {
			System.out.format("Reading InspectionHistory: u:%s f:%s l:%s e:%s p:%s \n",
					i.getInspectionHistoryKey(), i.getEstablishmentName(), i.getInspectionDate(), i.getInspectionResult(), 
					i.getFoodEstablishmentFK());
		}
		
		
		ViolationHistory vioh1 = violationHistoryDao.getByViolationHistoryKey(3);
		System.out.format("Reading InspectionHistory:i:%d n:%s d:%s m:%s h:%s a:%b c:%s s:%s t:%s y:%s e:%s z:%d p:%s \n",
				vioh1.getViolationHistoryKey(), vioh1.getEstablishmentName(), vioh1.getViolationCode(), 
				vioh1.getViolationLevel(), vioh1.getViolationDescription(), vioh1.getViolationDate(),
				vioh1.getViolationStatus(),vioh1.getViolationComments(),vioh1.getFoodEstablishmentFK(),
				vioh1.getInspectionHistoryFK());		
		
		ViolationHistory vioh2 = violationHistoryDao.getViolationHistoryByFoodEstFK(10);
		System.out.format("Reading InspectionHistory: i:%d n:%s d:%s m:%s h:%s a:%b c:%s s:%s t:%s y:%s e:%s z:%d p:%s \n",
				vioh2.getViolationHistoryKey(), vioh2.getEstablishmentName(), vioh2.getViolationCode(), 
				vioh2.getViolationLevel(), vioh2.getViolationDescription(), vioh2.getViolationDate(),
				vioh2.getViolationStatus(),vioh2.getViolationComments(),vioh2.getFoodEstablishmentFK(),
				vioh2.getInspectionHistoryFK());
		
		ViolationHistory vioh3 = violationHistoryDao.getViolationHistoryByInspHisFK(5444);
		System.out.format("Reading InspectionHistory: i:%d n:%s d:%s m:%s h:%s a:%b c:%s s:%s t:%s y:%s e:%s z:%d p:%s \n",
				vioh3.getViolationHistoryKey(), vioh3.getEstablishmentName(), vioh3.getViolationCode(), 
				vioh3.getViolationLevel(), vioh3.getViolationDescription(), vioh3.getViolationDate(),
				vioh3.getViolationStatus(),vioh3.getViolationComments(),vioh3.getFoodEstablishmentFK(),
				vioh3.getInspectionHistoryFK());
		
		List<ViolationHistory> viohList1 = violationHistoryDao.getViolationHistoryByVCode("15-4-202.16");
		for(ViolationHistory v : viohList1) {
			System.out.format("Reading ViolationHistory: i:%d n:%s d:%s m:%s h:%s a:%b c:%s s:%s t:%s y:%s e:%s z:%d p:%s \n",
					v.getViolationHistoryKey(), v.getEstablishmentName(), v.getViolationCode(), 
					v.getViolationLevel(), v.getViolationDescription(), v.getViolationDate(),
					v.getViolationStatus(),v.getViolationComments(),v.getFoodEstablishmentFK(),
					v.getInspectionHistoryFK());
		}
		
		List<ViolationHistory> viohList2 = violationHistoryDao.getViolationHistoryByVLevel("*");
		for(ViolationHistory v : viohList2) {
			System.out.format("Reading ViolationHistory: i:%d n:%s d:%s m:%s h:%s a:%b c:%s s:%s t:%s y:%s e:%s z:%d p:%s \n",
					v.getViolationHistoryKey(), v.getEstablishmentName(), v.getViolationCode(), 
					v.getViolationLevel(), v.getViolationDescription(), v.getViolationDate(),
					v.getViolationStatus(),v.getViolationComments(),v.getFoodEstablishmentFK(),
					v.getInspectionHistoryFK());
		}
		
		List<ViolationHistory> viohList3 = violationHistoryDao.getViolationHistoryByVDate(null);
		for(ViolationHistory v : viohList3) {
			System.out.format("Reading ViolationHistory: i:%d n:%s d:%s m:%s h:%s a:%b c:%s s:%s t:%s y:%s e:%s z:%d p:%s \n",
					v.getViolationHistoryKey(), v.getEstablishmentName(), v.getViolationCode(), 
					v.getViolationLevel(), v.getViolationDescription(), v.getViolationDate(),
					v.getViolationStatus(),v.getViolationComments(),v.getFoodEstablishmentFK(),
					v.getInspectionHistoryFK());
		}
		
		List<ViolationHistory> viohList4 = violationHistoryDao.getViolationHistoryByVStatus(ViolationStatus.Fail);
		for(ViolationHistory v : viohList4) {
			System.out.format("Reading ViolationHistory: i:%d n:%s d:%s m:%s h:%s a:%b c:%s s:%s t:%s y:%s e:%s z:%d p:%s \n",
					v.getViolationHistoryKey(), v.getEstablishmentName(), v.getViolationCode(), 
					v.getViolationLevel(), v.getViolationDescription(), v.getViolationDate(),
					v.getViolationStatus(),v.getViolationComments(),v.getFoodEstablishmentFK(),
					v.getInspectionHistoryFK());
		}

		//UPDATE
	    //Before update
		System.out.format("Reading result before update: c:%s e:%s u:%s f:%s t:%s \n",
				inspectionHistory1.getInspectionHistoryKey(), inspectionHistory1.getEstablishmentName(), inspectionHistory1.getInspectionDate(), 
				inspectionHistory1.getInspectionResult(), inspectionHistory1.getFoodEstablishmentFK());
		//updating
	    inspectionHistoryDao.updateInspectionResult(inspectionHistory3, "HE_Fail");
	    //after update
	    System.out.format("Reading result after update: c:%s e:%s u:%s f:%s t:%s \n",
				inspectionHistory1.getInspectionHistoryKey(), inspectionHistory1.getEstablishmentName(), inspectionHistory1.getInspectionDate(), 
				inspectionHistory1.getInspectionResult(), inspectionHistory1.getFoodEstablishmentFK());
		
	    
	    // Before update
	    System.out.format("Reading comments before update: i:%d n:%s d:%s m:%s h:%s a:%b c:%s s:%s t:%s y:%s \n",
	    		violationHistory5.getViolationHistoryKey(), violationHistory5.getEstablishmentName(), violationHistory5.getViolationCode(), 
	    		violationHistory5.getViolationLevel(), violationHistory5.getViolationDescription(), violationHistory5.getViolationDate(),
	    		violationHistory5.getViolationStatus(),violationHistory5.getViolationComments(),violationHistory5.getFoodEstablishmentFK(),
	    		violationHistory5.getInspectionHistoryFK());
	    // Update
	    violationHistoryDao.updateViolationComments(violationHistory5, "It is updated");
	    // After update
	    System.out.format("Reading comments before update: i:%d n:%s d:%s m:%s h:%s a:%b c:%s s:%s t:%s y:%s \n",
	    		violationHistory5.getViolationHistoryKey(), violationHistory5.getEstablishmentName(), violationHistory5.getViolationCode(), 
	    		violationHistory5.getViolationLevel(), violationHistory5.getViolationDescription(), violationHistory5.getViolationDate(),
	    		violationHistory5.getViolationStatus(),violationHistory5.getViolationComments(),violationHistory5.getFoodEstablishmentFK(),
	    		violationHistory5.getInspectionHistoryFK()) ;
	    
	    
		//DELETE
	    // Inspections before
        // delete
	    inspectionHistoryDao.delete(inspectionHistory4);
	    //after delete
        
        InspectionHistory isphDel = inspectionHistoryDao.getInspectionHistoryByISHKey(inspectionHistory4.getInspectionHistoryKey());
        if(isphDel==null) {
            System.out.print("InspectionHistory4 deleted\n");
        }
        else {
            System.out.print("InspectionHistory4 not deleted\n");
        }
        
        // Violations before
        // delete
	    violationHistoryDao.delete(violationHistory5);
	    //after delete
        
        ViolationHistory vioDel = violationHistoryDao.getByViolationHistoryKey(violationHistory5.getViolationHistoryKey());
        if(vioDel==null) {
            System.out.print("ViolationHistory5 deleted\n");
        }
        else {
            System.out.print("ViolationHistory5 not deleted\n");
        }*/
	}
}
