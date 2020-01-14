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

@WebServlet("/findinspectionbyinspectionkey")
public class FindInspectionByInspectionKey extends HttpServlet {
	
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

        InspectionHistory inspectionHistory = null;
        
        // Retrieve and validate key.
        // inspection key is retrieved from the URL query string.
        String inspectionKeyString = req.getParameter("inspectionkey");
        if (inspectionKeyString == null || inspectionKeyString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid key.");
        } else {
        	// Retrieve InspectionHistory, and store as a message.
        	try {
        		int inspectionKey = Integer.parseInt(inspectionKeyString);
        		inspectionHistory = inspectionHistoryDao.getInspectionHistoryByISHKey(inspectionKey);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + inspectionKeyString);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindInspectionsByInspectionKey.jsp.
        	messages.put("previousInspectionKey", inspectionKeyString);
        }
        req.setAttribute("inspectionHistory", inspectionHistory);
        
        req.getRequestDispatcher("/FindInspectionByInspectionKey.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        InspectionHistory inspectionHistory = null;
        
        // Retrieve and validate key.
        // key is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindInspectionByInspectionKey.jsp).
        String inspectionKeyString = req.getParameter("inspectionkey");
        if (inspectionKeyString == null || inspectionKeyString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid key.");
        } else {
        	// Retrieve inspection, and store as a message.
        	try {
        		int inspectionKey = Integer.parseInt(inspectionKeyString);
        		inspectionHistory = inspectionHistoryDao.getInspectionHistoryByISHKey(inspectionKey);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + inspectionKeyString);
        }
        req.setAttribute("inspectionHistory", inspectionHistory);
        
        req.getRequestDispatcher("/FindInspectionByInspectionKey.jsp").forward(req, resp);
    }
}
