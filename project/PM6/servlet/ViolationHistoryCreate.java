package food.servlet;


import food.dal.*;
import food.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/violationhistorycreate")
public class ViolationHistoryCreate extends HttpServlet {
	
	protected ViolationHistoryDao violationHistoryDao;
	
	@Override
	public void init() throws ServletException {
		violationHistoryDao = ViolationHistoryDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Just render the JSP.   
        req.getRequestDispatcher("/ViolationHistoryCreate.jsp").forward(req, resp);
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
		InspectionHistoryDao inspectionHistoryDao = InspectionHistoryDao.getInstance();

        // Retrieve and validate name.
        String establishmentName = req.getParameter("establishmentname");
        if (establishmentName == null || establishmentName.trim().isEmpty()) {
            messages.put("success", "Invalid EstablishmentName");
        } else {
        	// Create the ViolationHistory.
        	String violationCode = req.getParameter("violationcode");
        	String violationLevel = req.getParameter("violationlevel");
        	String violationDescription = req.getParameter("violationdescription");
        	
        	// ViolationDate must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	String stringViolationDate = req.getParameter("violationdate");
        	Date violation = new Date();
        	try {
        		violation = dateFormat.parse(stringViolationDate);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
        	String violationStatus = req.getParameter("violationstatus");
        	String violationComments = req.getParameter("violationcomments");

        	try {
				int foodEstabFK = Integer.parseInt(req.getParameter("foodestablishmentFK"));
	        	FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
				
				int insHisFK = Integer.parseInt(req.getParameter("inspectionhistoryFK"));
	        	InspectionHistory inspectionHistoryFK = inspectionHistoryDao.getInspectionHistoryByISHKey(insHisFK);
	        	
	        	ViolationHistory violationHistory = new ViolationHistory(establishmentName, violationCode, violationLevel, violationDescription, violation, violationStatus, violationComments, foodEstablishmentFK, inspectionHistoryFK);
	        	violationHistory = violationHistoryDao.create(violationHistory);
	        	messages.put("success", "Successfully created " + establishmentName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
                
        req.getRequestDispatcher("/ViolationHistoryCreate.jsp").forward(req, resp);
    }
}

