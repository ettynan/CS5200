package food.servlet;

import food.dal.*;
import food.model.*;

import java.io.IOException;
import java.sql.SQLException;
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

@WebServlet("/findinspectionsbyestname")
public class FindInspectionsByEstName extends HttpServlet {
	
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

        List<InspectionHistory> inspectionHistory = new ArrayList<InspectionHistory>();
        
        // Retrieve and validate name. 
        // estname is retrieved from the URL query string.
        String establishmentName = req.getParameter("establishmentname");
        if (establishmentName == null || establishmentName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve inspection, and store as a message.
        	try {
        		inspectionHistory = inspectionHistoryDao.getInspectionHistoryByEstablishmentName(establishmentName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + establishmentName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindInspectionsByEstName.jsp.
        	messages.put("previousEstablishmentName", establishmentName);
        }
        req.setAttribute("inspectionHistory", inspectionHistory);
        
        req.getRequestDispatcher("/FindInspectionsByEstName.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<InspectionHistory> inspectionHistory = new ArrayList<InspectionHistory>();
        
        // Retrieve and validate name.
        // establishment is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindInspectionsByEstName.jsp).
        String establishmentName = req.getParameter("establishmentname");
        if (establishmentName == null || establishmentName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve inspection, and store as a message.
        	try {
        		inspectionHistory = inspectionHistoryDao.getInspectionHistoryByEstablishmentName(establishmentName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + establishmentName);
        }
        req.setAttribute("inspectionHistory", inspectionHistory);
        
        req.getRequestDispatcher("/FindInspectionsByEstName.jsp").forward(req, resp);
    }
}
