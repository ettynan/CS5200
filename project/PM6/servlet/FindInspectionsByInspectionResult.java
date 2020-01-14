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

@WebServlet("/findinspectionsbyinspectionresult")
public class FindInspectionsByInspectionResult extends HttpServlet {
	
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
        
        // Retrieve and validate result. 
        // result is retrieved from the URL query string.
        String result = req.getParameter("result");
        if (result == null || result.trim().isEmpty()) {
            messages.put("success", "Please enter a valid result.");
        } else {
        	// Retrieve InspectionHistory, and store as a message.
        	try {
        		inspectionHistory = inspectionHistoryDao.getInspectionHistoryByInspectionResult(result);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + result);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindInspectionsByInspectionResult.jsp.
        	messages.put("previousInspectionResult", result);
        }
        req.setAttribute("inspectionHistory", inspectionHistory);
        
        req.getRequestDispatcher("/FindInspectionsByInspectionResult.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<InspectionHistory> inspectionHistory = new ArrayList<InspectionHistory>();
        
        // Retrieve and validate result.
        // result is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindInspectionsByInspectionResult.jsp).
        String result = req.getParameter("result");
        if (result == null || result.trim().isEmpty()) {
            messages.put("success", "Please enter a valid result.");
        } else {
        	try {
        		inspectionHistory = inspectionHistoryDao.getInspectionHistoryByInspectionResult(result);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + result);
        }
        req.setAttribute("inspectionHistory", inspectionHistory);
        
        req.getRequestDispatcher("/FindInspectionsByInspectionResult.jsp").forward(req, resp);
    }
}
