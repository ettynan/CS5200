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

@WebServlet("/findviolationsbyinspectionkey")
public class FindViolationsByInspectionKey extends HttpServlet {
	
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

        List<ViolationHistory> violationHistory = new ArrayList<ViolationHistory>();
        
        // Retrieve and validate inspection key.
        // inspection key is retrieved from the URL query string.
        String inspectionKeyString = req.getParameter("inspectionkey");
        if (inspectionKeyString == null || inspectionKeyString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid inspection key.");
        } else {
        	// Retrieve ViolationHistory, and store as a message.
        	try {
        		int inspectionKey = Integer.parseInt(inspectionKeyString);
        		violationHistory = violationHistoryDao.getViolationHistoryByInspHisFK(inspectionKey);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + inspectionKeyString);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindViolationsByInspectionKey.jsp.
        	messages.put("previousInspectionKey", inspectionKeyString);
        }
        req.setAttribute("violationHistory", violationHistory);
        
        req.getRequestDispatcher("/FindViolationsByInspectionKey.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<ViolationHistory> violationHistory = new ArrayList<ViolationHistory>();
        
        // Retrieve and validate key.
        // fekey is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindViolationsByInspectionKey.jsp).
        String inspectionKeyString = req.getParameter("inspectionkey");
        if (inspectionKeyString == null || inspectionKeyString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid key.");
        } else {
        	// Retrieve violations, and store as a message.
        	try {
        		int inspectionKey = Integer.parseInt(inspectionKeyString);
        		violationHistory = violationHistoryDao.getViolationHistoryByInspHisFK(inspectionKey);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + inspectionKeyString);
        }
        req.setAttribute("violationHistory", violationHistory);
        
        req.getRequestDispatcher("/FindViolationsByInspectionKey.jsp").forward(req, resp);
    }
}
