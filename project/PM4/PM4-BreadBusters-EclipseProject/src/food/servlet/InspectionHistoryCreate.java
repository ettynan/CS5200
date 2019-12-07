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


@WebServlet("/inspectionhistorycreate")
public class InspectionHistoryCreate extends HttpServlet {
	
	protected InspectionHistoryDao inspectionHistoryDao;
	
	@Override
	public void init() throws ServletException {
		inspectionHistoryDao = InspectionHistoryDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/InspectionHistoryCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();

        // Retrieve and validate name.
        String establishmentName = req.getParameter("establishmentname");
        if (establishmentName == null || establishmentName.trim().isEmpty()) {
            messages.put("success", "Invalid EstablishmentName");
        } else {
        	// Create the InspectionHistory.
        	//int inspectionHistoryKey = Integer.parseInt(req.getParameter("inspectionhistorykey"));
        	String inspectionResult = req.getParameter("inspectionresult");
        	
        	// InspectionDate must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	String stringInspectionDate = req.getParameter("inspectiondate");
        	Date inspection = new Date();
        	try {
        		inspection = dateFormat.parse(stringInspectionDate);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
	        try {
	        	int foodEstabFK = Integer.parseInt(req.getParameter("foodestablishmentFK"));
	        	FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
	        	InspectionHistory inspectionHistory = new InspectionHistory(establishmentName, inspection, inspectionResult, foodEstablishmentFK);
	        	inspectionHistory = inspectionHistoryDao.create(inspectionHistory);
	        	messages.put("success", "Successfully created " + establishmentName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/InspectionHistoryCreate.jsp").forward(req, resp);
    }
}

