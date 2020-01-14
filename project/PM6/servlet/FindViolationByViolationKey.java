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

@WebServlet("/findviolationbyviolationkey")
public class FindViolationByViolationKey extends HttpServlet {
	
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

        ViolationHistory violationHistory = null;
        
        // Retrieve and validate key.
        // violation key is retrieved from the URL query string.
        String violationHistoryString = req.getParameter("violationkey");
        if (violationHistoryString == null || violationHistoryString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid key.");
        } else {
        	// Retrieve violation, and store as a message.
        	try {
        		int violationKey = Integer.parseInt(violationHistoryString);
        		violationHistory = violationHistoryDao.getByViolationHistoryKey(violationKey);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + violationHistoryString);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindViolaitionByViolationKey.jsp.
        	messages.put("previousInspectionKey", violationHistoryString);
        }
        req.setAttribute("violationHistory", violationHistory);
        
        req.getRequestDispatcher("/FindViolationByViolationKey.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        ViolationHistory violationHistory = null;
        
        // Retrieve and validate key.
        // key is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindViolationByViolationKey.jsp).
        String violationHistoryString = req.getParameter("violationkey");
        if (violationHistoryString == null || violationHistoryString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid key.");
        } else {
        	// Retrieve violation, and store as a message.
        	try {
        		int violationKey = Integer.parseInt(violationHistoryString);
        		violationHistory = violationHistoryDao.getByViolationHistoryKey(violationKey);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + violationHistoryString);
        }
        req.setAttribute("violationHistory", violationHistory);
        
        req.getRequestDispatcher("/FindViolationByViolationKey.jsp").forward(req, resp);
    }
}
